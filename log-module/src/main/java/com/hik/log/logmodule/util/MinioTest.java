package com.hik.log.logmodule.util;

import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.policy.PolicyType;
import org.xmlpull.v1.XmlPullParserException;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MinioTest {

  public static void main(String[] args) throws IOException, InvalidPortException, InvalidEndpointException, NoSuchAlgorithmException, InsufficientDataException, InternalException, InvalidKeyException, InvalidBucketNameException, ErrorResponseException {
      InputStream inputStream = new FileInputStream(new File("/Users/apple/Desktop/毕业实习报告.docx"));
      MinioClient minioClient = new MinioClient("http://localhost:9000","minioadmin","minioadmin");
      try {
          minioClient.bucketExists("hikvision");
          minioClient.makeBucket("hikvision","hik");
          minioClient.setBucketPolicy("hikvision","hik", PolicyType.READ_WRITE);
          minioClient.putObject("hikvision","毕业实习报告.docx",inputStream,
                  inputStream.available(),"application/octet-stream");
          String hikvision = minioClient.getObjectUrl("hikvision", "123.docx");
          System.out.println(hikvision);

      } catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException | XmlPullParserException e) {
          e.printStackTrace();
      }
  }
}
