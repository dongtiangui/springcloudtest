package com.hik.common.commonmodule.utils;
import org.apache.commons.vfs2.FileName;
import org.pentaho.di.core.ResultFile;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.logging.LogLevel;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.repository.RepositoryDirectoryInterface;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * kettle转换、作业工具类
 */
@Component
public class KettleUtils {
    private static final Logger log = LoggerFactory.getLogger(KettleUtils.class);
    @Autowired
    private KettleDatabaseRepository repository;
    @Autowired
    private FTPProperties ftpProperties;
    @Autowired
    private FTPUtils ftpUtils;
    public String runTrans(String rootPath,String transName,String[] params) throws KettleException {
        String fileUrl;
        //根据变量查找到模型所在的目录对象。
        RepositoryDirectoryInterface directory = repository.findDirectory(rootPath);
        //创建ktr元对象
        TransMeta transformationMeta = repository.loadTransformation(transName, directory, null, true, null);
        //创建ktr
        Trans trans = new Trans(transformationMeta);
        trans.setLogLevel(LogLevel.ERROR);
        //执行ktr
        trans.execute(params);
        //等待执行完毕
        trans.waitUntilFinished();
        if (trans.getErrors() > 0) {
            log.info("trans executed failed");
        } else {
            log.info("trans executed OK");
            log.info("start file upload");
            try{
                List<ResultFile> filesList = trans.getResult().getResultFilesList();
                fileUrl = uploadAnDelete(filesList);
                return fileUrl;
            }catch (Exception e){
                log.info("trans executed failed");
                e.printStackTrace();
            }
        }
        return "error";
    }
    public void runJob(String rootPath,String jobName,String[] params) throws KettleException {
        RepositoryDirectoryInterface dir = repository.findDirectory(rootPath);//根据指定的字符串路径 找到目录
        //加载指定的job
        JobMeta jobMeta = ((Repository) repository).loadJob(jobName, dir, null, null);
        Job job = new Job(repository, jobMeta);
        job.setLogLevel(LogLevel.ERROR);
        job.run();
        job.waitUntilFinished();//等待job执行完；
        job.setFinished(true);
//        System.out.println(job.getResult());
    }

    private String uploadAnDelete(List<ResultFile> filesList){
        AtomicReference<FileName> fileName = new AtomicReference<>();
       try{
           filesList.forEach(item -> {
               fileName.set(item.getFile().getName());
               String path = fileName.get().getPath();
               try {
                   File file = new File(path);
                   InputStream inputStream = new FileInputStream(file);
                   ftpUtils.uploadFile(fileName.get().getBaseName(),inputStream);
                   if (file.exists()) {
                       file.delete();
                   }
               } catch (FileNotFoundException e) {
                   e.printStackTrace();
               }
           });
       }catch (Exception e){
           log.error(e.getMessage());
       }
        return ftpProperties.getFileurl()+ fileName.get().getBaseName();
    }
}
