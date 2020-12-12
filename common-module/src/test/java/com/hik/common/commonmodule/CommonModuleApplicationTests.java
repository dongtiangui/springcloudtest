package com.hik.common.commonmodule;

import com.hik.common.commonmodule.dao.PictureJpa;
import com.hik.common.commonmodule.services.CommonServices;
import com.hik.common.commonmodule.utils.FTPUtils;
import com.hik.common.commonmodule.utils.KettleUtils;
import com.hik.common.commonmodule.utils.NginxUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pentaho.di.core.exception.KettleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CommonModuleApplicationTests {


  @Autowired
  private KafkaTemplate<String, String> template;

  @Autowired
  private CommonServices commonServices;

  @Autowired
  private PictureJpa pictureJpa;


  @Autowired
  private KettleUtils kettleUtils;

  @Autowired
  private FTPUtils ftpUtils;

  @Test
  public void contextLoads() throws FileNotFoundException, KettleException {
    //    File file = new File("/Users/apple/Downloads/1121.png");
    //    InputStream inputStream = new FileInputStream(file);
    //    String filePath = new DateTime().toString("/yyyyMMdd/");
    //    String newName = NginxUtil.genImageName();
    //    String oldName = file.getName();
    //    newName = newName + oldName.substring(oldName.lastIndexOf("."));
    //    String images = NginxUtil.putImages(inputStream, filePath, newName);
    //    System.out.println(images);

    //    ftpClientService.uploadFile("/Users/apple/Downloads/1121.png","/home/ftpuser");
    //    ftpClientService.download("/home/ftpuser","机器人导论.pdf","/Users/apple/Downloads/机器人导论.pdf");
    //    ftpClientService.readFileToBase64("机器人导论.pdf","/home/ftpuser");

    //    FtpUtil.delImages(new DateTime().toString("/yyyyMMdd/")+"1585137492261413.png");

    //        while (true){
    //          new Thread(() -> {
    //            while (true){
    //              try {
    //                Thread.sleep(3000);
    //                template.send("etl-hik","key","生产数据："+new Random().nextDouble()*10000);
    //              } catch (InterruptedException e) {
    //                e.printStackTrace();
    //              }
    //            }
    //          }).run();
    //        }

    //    uploadFile(ftpClient,"/Users/apple/Downloads/1121.png","/home/ftpuser");

    //    boolean flag = FTPUtils.uploadFile("hrabbit.png", "/Users/apple/Downloads/1121.png");
    //    Assert.assertTrue(flag);

    //      Map<String,Object> param = new HashMap<>();
    //
    //      param.put("local","贵州");
    //      param.put("type","网络摄像机");
    //      param.put("pic_url","http://localhost:8080");
    //      commonServices.insertSR(param);

//    commonServices.getImageUrl().forEach(System.out::println);
//
//    kettleUtils.runTrans("/hik_etl/excel","excel_dababase",null);
//    Map<String,Object> map = new HashMap<>();
//    map.put("local","黔西");
//    map.put("type","网络摄像机");
//    map.put("pic_url","http://www.baidu.com");
//    String sr = commonServices.insertSR(map);
//    System.out.println(sr);

//JDBC_HOST
//JDBC_PORT
//JDBC_USERNAME
//
//JDBC_PASSWORD
//JDBC_DBNAME

    InputStream inputStream = new FileInputStream(new File("/Users/apple/Downloads/1121.png"));
    System.out.println(ftpUtils.uploadFile("1121.png",inputStream));
  }
}
