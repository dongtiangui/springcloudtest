package com.hik.common.commonmodule.services;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.hik.common.commonmodule.dao.PictureJpa;
import com.hik.common.commonmodule.dao.SnapshotRecordJpa;
import com.hik.common.commonmodule.domian.PictureInfo;
import com.hik.common.commonmodule.domian.SnapshotRecord;
import com.hik.common.commonmodule.utils.ExcelUtils;
import com.hik.common.commonmodule.utils.FTPProperties;
import com.hik.common.commonmodule.utils.KettleUtils;
import lombok.extern.slf4j.Slf4j;
import org.pentaho.di.core.exception.KettleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
@Slf4j
@Service
/**
 * 公共服务接口实现
 */
public class CommonServicesImpl implements CommonServices {
    @Autowired
    private SnapshotRecordJpa snapshotRecordJpa;
    @Autowired
    private PictureJpa pictureJpa;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private KettleUtils kettleUtils;
    @Autowired
    private FTPProperties ftpProperties;

    /**
     * 查询所有的抓拍图片的URL
     * @return
     */
    @Override
    public List<String> getImageUrl() {
        ArrayList<String> list = Lists.newArrayList();
        pictureJpa.findAll().forEach(item-> list.add(item.getPic_url()));
        log.info("查询完成！{}",list);
        return list;
    }

    /**
     * 根据抓拍信息查询所有的抓拍图片的URL
     * @param dr_id
     * @return
     */
    @Override
    public List<String> getImageUrlByDr(String dr_id) {
        ArrayList<String> pic_list = new ArrayList<>();
        List<PictureInfo> snapshotRecord_d_id = pictureJpa.findPictureInfosBySnapshotRecord_D_id(dr_id);
        snapshotRecord_d_id.forEach(item-> pic_list.add( item.getPic_url()));
        return pic_list;
    }

    @Override
    public String getImageUrlByName(String picName) {
        return pictureJpa.findByPic_name(picName).getPic_url();
    }

    /**
     * 插入图片信息
     * @param pictureInfo
     * @return
     */

    @Override
    public PictureInfo insertImage(PictureInfo pictureInfo) {
        return pictureInfo!=null?pictureJpa.saveAndFlush(pictureInfo):new PictureInfo();
    }

    /**
     * 插入一个抓拍信息并插入抓拍图片信息
     * @param param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String insertSR(Map<String, Object> param) {
        String fileUrl = "";
        SnapshotRecord record;
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            String d_id = this.GeneratedDid();
            String d_local = (String) param.get("local");
            String d_type = (String) param.get("type");
            String d_pic_url = (String) param.get("pic_url");
            record = new SnapshotRecord();
            record.setD_id(d_id);
            record.setCreateTime(localDateTime);
            record.setD_local(d_local);
            record.setD_type(d_type);
            record.setImage_url(d_pic_url);
            // 消息发送kafka
            CompletableFuture<String> future = sendKafka(record);
            if (future.get().equals("success")){
                // 执行kettle转换
                fileUrl = kettleUtils.runTrans("/hik_etl/excel", "excel_dababase", null);
            }else {
                return "kafka send error";
            }
            String fileName=fileUrl.substring(fileUrl.lastIndexOf("/")+1);
            if (getFileByNetInsert(fileName)){
                return fileUrl;
            }
        } catch (KettleException e) {
            e.printStackTrace();
            return fileUrl;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            return "Future error";
        }
        return fileUrl;
    }
    /**
     * 查询所有抓拍信息
     * @return
     */
    @Override
    public List<SnapshotRecord> selectSRAll() {
        return snapshotRecordJpa.findAll();
    }

    /**
     * 根据ID查找抓拍信息
     * @param d_id
     * @return
     */
    @Override
    public SnapshotRecord selectRSById(String d_id) {
        Optional<SnapshotRecord> recordJpaById = snapshotRecordJpa.findById(d_id);
        return recordJpaById.orElseGet(() -> recordJpaById.orElse(new SnapshotRecord()));
    }

    /**
     * 根据ID删除抓拍信息
     * @param d_id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSRById(String d_id) {
        try{
            snapshotRecordJpa.deleteById(d_id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 多个ID删除抓拍信息
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSRByListId(String[] ids) {
        if(ids==null||ids.length==0){
           return false;
        }
        try{
            Lists.newArrayList(ids).forEach(item-> snapshotRecordJpa.deleteById(item));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 时间戳ID
     * @return
     */
    private String GeneratedDid(){
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int end3 = random.nextInt(999);
        return millis + String.format("%03d", end3);
    }

    /**
     * 异步执行消息发送kafka
     * @param context
     * @return
     */
    @Async("taskExecutor")
    public CompletableFuture<String> sendKafka(SnapshotRecord context){
        if (context == null) {
            log.error("context is null");
            return CompletableFuture.completedFuture("context is null");
        }
        try{
            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("etl-hik", context.getD_id(), JSON.toJSONString(context, true));
            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>(){
                @Override
                public void onSuccess(SendResult<String, String> result) {
                    log.info("kafka send is success!");
                }
                @Override
                public void onFailure(Throwable ex) {
                    log.error("kafka send is failure!");
                }
            });
            return CompletableFuture.completedFuture("success");
        }catch (Exception e){
            return CompletableFuture.completedFuture(e.getMessage());
        }
    }

    /**
     * 解析文件并插入数据库
     * @param fileName
     * @return
     */
    public boolean getFileByNetInsert(String fileName){
        URL url;
        try {
            url = new URL(ftpProperties.getFileurl()+fileName);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream stream = connection.getInputStream();
            List<String> list = ExcelUtils.readExcelFile(stream, fileName);
            snapshotRecordJpa.save(ParsingJsonToObject(list.get(0)));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 解析json
     * @param json
     * @return
     */
    private SnapshotRecord ParsingJsonToObject(String json){
        JSONObject outJson = JSONObject.parseObject(json);
        String createTime = outJson.getString("createTime");
        String d_id = outJson.getString("d_id");
        String d_local = outJson.getString("d_local");
        String d_type = outJson.getString("d_type");
        String image_url = outJson.getString("image_url");
        String replace = createTime.replace("T", " ");
        String resultCreateTime=replace.substring(0,replace.indexOf("."));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(resultCreateTime,df);
        try {
            this.savePicToFile(image_url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new SnapshotRecord(d_id,d_local,d_type,ldt,image_url);
    }

    /**
     * 保存图片到本地磁盘
     * @param pic_url
     * @throws IOException
     */
    private void savePicToFile(String pic_url) throws IOException {
        OutputStream os = null;
        String fileName=pic_url.substring(pic_url.lastIndexOf("/")+1);
        URL url = new URL(pic_url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream stream = connection.getInputStream();
        String staticPath = Objects.requireNonNull(Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource("static")).getPath();
        String url_path = "images" + File.separator + fileName;
        String savePath = staticPath + File.separator + url_path;
        String visitPath ="static/" + url_path;
        try {
            byte[] bs = new byte[1024];
            int len;
            File tempFile = new File(savePath);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            while ((len = stream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert os != null;
                os.close();
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
