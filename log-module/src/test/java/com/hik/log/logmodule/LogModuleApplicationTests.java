package com.hik.log.logmodule;

import com.hik.log.logmodule.util.MinioTemplate;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.protobuf.generated.ClientProtos;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogModuleApplicationTests {

//  @Autowired
//  private KafkaTemplate<String, String> template;

  @Autowired
  private HbaseTemplate hbaseTemplate;

  @Autowired
  private FileSystem fileSystem;

//  @Autowired
//  private RedisUtil redisUtil;
//
//  @Autowired
//  private BaseElasticService baseElasticService;
//
//  @BeforeClass
//  public static void set(){
//    System.setProperty("es.set.netty.runtime.available.processors", "false");
//  }
  @Test
  public void contextLoads() {

//    hbaseTemplate.put("hikvision:user","rowKey1","name","name1","海康威视".getBytes());

//    try {
//        new Thread(() -> {
//          while (true){
//            try {
//              Thread.sleep(3000);
//              template.send("hik-topic",1,"key","生产数据："+new Random().nextDouble()*10000);
//            } catch (InterruptedException e) {
//              e.printStackTrace();
//            }
//          }
//        }).run();

//      List<String> rowKeys = new LinkedList<>();
//      rowKeys.add("rowkey1");
//      List<Result> data = getListRowKeyData("user", rowKeys, "name", "name1");
//
//      System.out.println(data.toString()+"hbase查询");


//    try {
//      fileSystem.mkdirs(new Path("/hadoop/hikvision"));
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

    MinioTemplate.getInstance().createBucketPublic("huawei");

//    try {
//      fileSystem.copyFromLocalFile(new Path("/Users/apple/Desktop/毕业论文/毕业论文.pdf"),new Path("/hadoop/hikvision"));
//
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    try (FileOutputStream outputStream = new FileOutputStream(new File("/Users/apple/Desktop/毕业论文1.pdf"));
//         FSDataInputStream inputStream = fileSystem.open(new Path("/hadoop/hikvision/毕业论文.pdf"), 1024)){
//      IOUtils.copy(inputStream,outputStream);
//      RemoteIterator<LocatedFileStatus> locatedFileStatusRemoteIterator = fileSystem.listFiles(new Path("/"), true);
//      while (locatedFileStatusRemoteIterator.hasNext()){
//
//        LocatedFileStatus next = locatedFileStatusRemoteIterator.next();
//        System.out.println(next.getPath().toString());
//      }
//
//      fileSystem.close();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

//    } catch (Exception e) {
//      System.out.println(e.getMessage());
//    }
  }

  public List<Result> getListRowKeyData(String tableName, List<String> rowKeys, String familyColumn, String column) {
    return rowKeys.stream().map(rk -> {
      if (StringUtils.isNotBlank(familyColumn)) {
        if (StringUtils.isNotBlank(column)) {
          return hbaseTemplate.get(tableName, rk, familyColumn, column, (rowMapper, rowNum) -> rowMapper);
        } else {
          return hbaseTemplate.get(tableName, rk, familyColumn, (rowMapper, rowNum) -> rowMapper);
        }
      }
      return hbaseTemplate.get(tableName, rk, (rowMapper, rowNum) -> rowMapper);
    }).collect(Collectors.toList());
  }
}
