package com.hik.common.commonmodule;

import com.alibaba.fastjson.JSONObject;
import com.hik.common.commonmodule.domian.SnapshotRecord;
import com.hik.common.commonmodule.utils.ExcelUtils;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import org.junit.Test;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.plugins.PluginFolder;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.repository.*;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class FtpTest {
  @Test
  public void ftpTest() throws KettleException, FileNotFoundException {
    /** 需要先加载插件文件 */
//              PluginFolder folder = new
//     PluginFolder("/Users/apple/Desktop/springcloudtest/common-module/src/main/resources/plugins/pentaho-kafka-consumer", true, true);
//              StepPluginType.getInstance().getPluginFolders().add(folder);
    //          KettleEnvironment.init();
    //          TransMeta transMeta = new TransMeta("/Users/apple/Desktop/kafaka.ktr");
    //          Trans trans = new Trans(transMeta);
    //          trans.execute(null);
    //          trans.setLogLevel(LogLevel.ROWLEVEL);
    //          trans.waitUntilFinished();

//    KettleDatabaseRepository obj = this.RepositoryCon();
//      if (obj != null) {// 资源库连接成功
//          runTrans(obj, "hikvision_etl");// 调用指定的tansformation
//      }


//    List<File> files = searchFiles(ResourceUtils.getFile("classpath:kafka.xls2020"), "0");
//    System.out.println("共找到:" + files.size() + "个文件");
//    for (File file : files) {
//      System.out.println(file.getAbsolutePath());
//    }
    Function<Integer,Integer> function = x -> x+1;

    Integer apply = function.apply(1);
    System.out.println(apply);

    Consumer<Integer> consumer = System.out::println;

    consumer.accept(12);


  }



  public  KettleDatabaseRepository RepositoryCon() throws KettleException {
    // 初始化
    KettleEnvironment.init();
    // 数据库形式的资源库对象
    KettleDatabaseRepository repository = new KettleDatabaseRepository();
    // 数据库连接元对象
    // （kettle数据库连接名称(KETTLE工具右上角显示)，资源库类型，连接方式，IP，数据库名，端口，用户名，密码） //cgmRepositoryConn
    DatabaseMeta databaseMeta =
        new DatabaseMeta(
            "hik_etl",
            "postgresql",
            "Native(JDBC)",
            "127.0.0.1",
            "hik_etl",
            "5432",
            "postgres",
            "520131");
    // 数据库形式的资源库元对象
    KettleDatabaseRepositoryMeta kettleDatabaseMeta =
            new KettleDatabaseRepositoryMeta("hik", "enfo_bi", "king description",databaseMeta);
    // 用资源库元对象初始化资源库对象
    repository.init(kettleDatabaseMeta);
    // 连接到资源库
    repository.connect("admin", "admin"); // 默认的连接资源库的用户名和密码
    //创建ktr元对象
    System.out.println(repository.isConnected());
    if (repository.isConnected()) {
      System.out.println("连接成功");
      return repository;
    } else {
      System.out.println("连接失败");
      return null;
    }
  }
  public static void runTrans(KettleDatabaseRepository rep, String transName) {
    try{
      RepositoryDirectoryInterface dir = rep.findDirectory("/hik_etl");
      TransMeta transformationMeta = (rep.loadTransformation(transName, dir, null, true, null ));
      Trans trans = new Trans(transformationMeta);
      trans.execute(null);
      trans.waitUntilFinished(); // 等待转换执行结束
      if (trans.getErrors() != 0) {
        System.out.println("Error");
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  public static List<File> searchFiles(File folder, final String keyword) {
    List<File> result = new ArrayList<File>();
    if (folder.isFile()) result.add(folder);
    File[] subFolders =
            folder.listFiles(file -> {
              if (file.isDirectory()) {
                return true;
              }
              if (file.getName().toLowerCase().contains(keyword)) {
                return true;
              }
              return false;
            });
    if (subFolders != null) {
      for (File file : subFolders) {
        if (file.isFile()) {
          // 如果是文件则将文件添加到结果列表中
          result.add(file);
        } else {
          // 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
          result.addAll(searchFiles(file, keyword));
        }
      }
    }
    return result;
  }


}
