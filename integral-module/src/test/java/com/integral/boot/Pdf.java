package com.integral.boot;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class Pdf {

    @Test
    public void text(){

//    try (PDDocument document = PDDocument.load(new File("/Users/apple/Desktop/SAAS固定资产管理系统需求表.pdf"))) {
//      if (!document.isEncrypted()) {
//        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
//        stripper.setSortByPosition(true);
//        PDFTextStripper tStripper = new PDFTextStripper();
//        String pdfFileInText = tStripper.getText(document);
//          String[] lines = pdfFileInText.split("\\r?\\n");
//        for (String line : lines) {
//          System.out.println(line);
//        }
//      }
//    } catch (IOException e) {
//        e.printStackTrace();
//    }

//        try {
//            PDDocument document = PDDocument.load(new File("/Users/apple/Desktop/15595541132.pdf"));
//            if (document.isEncrypted()){
//                PDFTextStripperByArea area = new PDFTextStripperByArea();
//                area.setSortByPosition(true);
//                PDFTextStripper pdfTextStripper = new PDFTextStripper();
//                String text = pdfTextStripper.getText(document);
//                String[] lines = text.split("\\r?\\n");
//                for (String line : lines) {
//                    System.out.println(line);
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAI9YQCGxQMDtEh", "uzrq8HHR97rLhEgx4h1dQZTmy2EOYo");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", "18188175536");
        request.putQueryParameter("SignName", "董天贵");
        request.putQueryParameter("TemplateCode", "SMS_140085215");
        request.putQueryParameter("TemplateParam", "{'code':'123'}");
        request.putQueryParameter("SmsUpExtendCode", "400");
        request.putQueryParameter("OutId", "1223");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }
}
