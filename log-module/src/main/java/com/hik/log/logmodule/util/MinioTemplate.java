package com.hik.log.logmodule.util;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.policy.PolicyType;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 模板方法抽象类
 */
public class MinioTemplate {
    private final static Logger log = LoggerFactory.getLogger(MinioTemplate.class);

    private volatile static MinioTemplate minioTemplate;

    private static MinioClient minioClient;

    private static int RETRY_NUM = 3;

    private MinioTemplate(){
        init();
    }
    public static MinioTemplate getInstance() {
       if (minioTemplate == null){
           synchronized (MinioTemplate.class){
               if (minioTemplate == null){
                   minioTemplate = new MinioTemplate();
               }
           }
       }
       return minioTemplate;
    }

    /**
     * 初始化minio客户端
     */
    private void init(){
        try {
            PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration("minio.properties");
            String url = propertiesConfiguration.getString("minio.url","http://localhost:9000");
            String username = propertiesConfiguration.getString("minio.username", "minioadmin");
            String password = propertiesConfiguration.getString("minio.password", "minioadmin");
            if (StringUtils.isNotEmpty(url) && StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
                minioClient = new MinioClient(url, username, password, false);
            }
        } catch (Exception e) {
            log.error("restClient.close occur error", e);
        }
    }

    /**
     * 创建桶
     */
    public boolean createBucketPublic(String bucketName){
        try{
            minioClient.makeBucket(bucketName);
            minioClient.setBucketPolicy(bucketName,bucketName.substring(3), PolicyType.READ_WRITE);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return false;
    }

    /**
     * 文件路径上传
     * @param bucketName
     * @param minioFilePath
     * @param localFilePath
     * @param mediaType
     * @return
     */
    public String uploadFile(String bucketName,String minioFilePath,String localFilePath,String mediaType){
        log.info("uploadStream for bucketName={} minioFilePath={} inputStream.getclass={}, mediaType={}", bucketName,
                minioFilePath, localFilePath, mediaType);

        if (StringUtils.isBlank(mediaType)){
            mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        try{
            putObjectWithRetry(bucketName, minioFilePath, localFilePath, mediaType);
            return "";
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    /**
     * 文件流上传
     * @param bucketName
     * @param minioFilePath
     * @param inputStream
     * @param mediaType
     * @return
     */

    public String uploadSteam(String bucketName, String minioFilePath, InputStream inputStream,String mediaType){
        log.info("uploadStream for bucketName={} minioFilePath={} inputStream.getclass={}, mediaType={}", bucketName,
                minioFilePath, inputStream.getClass(), mediaType);

        if (StringUtils.isBlank(mediaType)){
            mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        try{
            putObjectWithRetry(bucketName, minioFilePath, inputStream, mediaType);
            return "";
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 核心上传类
     * @param bucketName
     * @param objectName
     * @param fileName
     * @param contentType
     */
    private void putObjectWithRetry(String bucketName, String objectName, String fileName, String contentType) {
        int current = 0;
        boolean isSuccess = false;
        while (!isSuccess && current < RETRY_NUM) {
            try {
                minioClient.putObject(bucketName,objectName,fileName,
                        contentType);
                isSuccess = true;
            } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidKeyException | IOException | NoSuchAlgorithmException e) {
                log.warn("[minio] putObject stream, ErrorResponseException occur for time =" + current, e);
                current++;
                e.printStackTrace();
            } catch (NoResponseException | XmlPullParserException | io.minio.errors.InvalidArgumentException e) {
                e.printStackTrace();
            }
        }
        if (current == RETRY_NUM) {
            log.error("[minio] putObject, backetName={}, objectName={}, failed finally!");
        }
    }

    /**
     *
     * @param bucketName
     * @param objectName
     * @param fileName
     * @param contentType
     */

    private void putObjectWithRetry(String bucketName, String objectName, InputStream fileName, String contentType)  {
        int current = 0;
        boolean isSuccess = false;
        while (!isSuccess && current < RETRY_NUM) {
            try {
                minioClient.putObject(bucketName,objectName,fileName,
                        contentType);
                isSuccess = true;
            } catch (ErrorResponseException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException  | InternalException e) {
                log.warn("[minio] putObject stream, ErrorResponseException occur for time =" + current, e);
                current++;
            } catch (NoResponseException | XmlPullParserException | InvalidArgumentException e) {
                e.printStackTrace();
            }
        }
        if (current == RETRY_NUM) {
            log.error("[minio] putObject, backetName={}, objectName={}, failed finally!");
        }
    }
}
