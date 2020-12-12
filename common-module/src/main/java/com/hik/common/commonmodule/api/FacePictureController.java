package com.hik.common.commonmodule.api;
import com.hik.common.commonmodule.domian.PictureInfo;
import com.hik.common.commonmodule.domian.SnapshotRecord;
import com.hik.common.commonmodule.services.CommonServices;
import com.hik.common.commonmodule.utils.NginxUtil;
import com.hik.common.commonmodule.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
/**
 * 图片服务
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/device/")
@Api(description= "服务接口")
@EnableCaching
public class FacePictureController  {
    private static final Logger logger = LoggerFactory.getLogger(FacePictureController.class);
    @Value("${nginx.img.url}")
    private String uploadDir;
    @Autowired
    private CommonServices commonServices;

    /**
     * 图片上传
     * @param dr_id
     * @param multipartFile
     * @return
     */

    @ApiOperation(value = "图片上传", notes="restful上传图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dr_id",value = "抓拍信息ID",paramType = "query",required = true,dataType = "String")
    })
    @PostMapping(value = "upPicture")
    public ResponseResult<String> updateImage(String dr_id,@RequestParam("imageFile") MultipartFile multipartFile) {
        ResponseResult<String> result = new ResponseResult<>();
        InputStream fileInputStream = null;
        try {
            fileInputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String newName = NginxUtil.genImageName();
        String oldName = multipartFile.getOriginalFilename();
        assert oldName != null;
        String filePath = new DateTime().toString("/yyyyMMdd/")+dr_id;
        newName = newName + oldName.substring(oldName.lastIndexOf("."));
        String images = NginxUtil.putImages(fileInputStream, filePath, newName);
        SnapshotRecord record = commonServices.selectRSById(dr_id);
        commonServices.insertImage(new PictureInfo(images,newName,LocalDateTime.now(),record));
        result.setCode(200);
        result.setMsg("success");
        result.setData(images);
        return result;
    }

    /**
     * 上传抓拍信息
     * @return
     */
    @ApiOperation(value = "抓拍信息上传", notes="通过抓拍基本信息post上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "local", value = "摄像机地点", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type",value = "摄像机类型",paramType = "query",required = true,dataType = "String"),
            @ApiImplicitParam(name = "pic_url",value = "抓拍图片的地址",paramType = "query",required = true,dataType = "String")
    })
    @PostMapping(value = "upInfo")
    public ResponseResult<String> updateInformation(@RequestBody Map<String,Object> param) {
        ResponseResult<String> result = new ResponseResult<>();
        String insertSR = commonServices.insertSR(param);
        if (!insertSR.equals("error")){
            result.setCode(200);
            result.setMsg("success");
            result.setData(insertSR);
            return result;
        }
        result.setCode(400);
        result.setMsg("error");
        result.setData(insertSR);
        return result;
    }

    /**
     * 图片下载
     * @param imageName
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "图片下载", notes="通过图片的名称下载文件")
    @ApiImplicitParam(name = "imageName", value = "图片文件名", paramType = "query", required = true, dataType = "String")
    @GetMapping(value = "downPicture")
    public ResponseResult<String> downloadImage(String imageName, HttpServletRequest request, HttpServletResponse response) {
        ResponseResult<String> result = new ResponseResult<>();
        String urlByName = commonServices.getImageUrlByName(imageName);
        byte[] buffer= new byte[1024];
        int len;
        //创建httpClient客户端
        CloseableHttpClient httpClient= HttpClients.createDefault();
        //创建httpGet发送请求获取文件
        HttpGet httpGet=new HttpGet(urlByName);
        //将文件读入到输入流中
        try(CloseableHttpResponse httpResponse=httpClient.execute(httpGet);
            InputStream input=httpResponse.getEntity().getContent();
            OutputStream output=response.getOutputStream()){
            //设置响应头
            response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode(imageName, "UTF-8"));       //从输入流中读取文件
            while((len=input.read(buffer))!=-1){
                output.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.setCode(200);
        result.setData("success");
        return result;
    }

    @ApiOperation(value = "获取抓拍机的所有图片", notes="通过抓拍信息的ID获取抓拍图片")
    @ApiImplicitParam(name = "dr_id", value = "抓拍信息的ID", paramType = "query", required = true, dataType = "String")
    @GetMapping(value = "getPictureByDic")
    public ResponseResult<List<String>> getImageByDate(String dr) {
        ResponseResult<List<String>> result = new ResponseResult<>();
        List<String> urlByDr = commonServices.getImageUrlByDr(dr);
        result.setData(urlByDr);
        result.setCode(200);
        result.setMsg("success");
        return result;
    }
}
