package com.alipay.demo.controller.rest;

import com.alipay.demo.pojo.UploadResponse;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MultivaluedMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class UploadControllerImpl implements UploadController {
    @Value("${demo.domain}")
    private String appDomain;

    @Override
    public UploadResponse upload(HttpServletRequest request, MultipartFormDataInput formData) throws Exception {
        UploadResponse response = new UploadResponse();

        //获取表单中的数据map
        Map<String, List<InputPart>> dataMaps = formData.getFormDataMap();

        //根据表单元素名称获取表单元素（需要与前端设置的值一致）
        List<InputPart> fileParts = dataMaps.get("file");

        //解析获取表单文件的输入流
        if (fileParts == null || fileParts.isEmpty()) {
            return response;
        }

        InputPart filePart = fileParts.get(0);
        InputStream inputStream = filePart.getBody(InputStream.class, null);

        //保存文件至本地（当前是应用根目录）
        String oldFileName = getFileName(filePart.getHeaders());
        String fileName = UUID.randomUUID() + getFileExtension(oldFileName);

        File target = new File(fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(target);
            byte[] b = new byte[1024];
            int readLength;
            while ((readLength = inputStream.read(b)) != -1) {
                fos.write(b, 0, readLength);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (fos != null) {
                fos.close();
            }
        }

        response.setImgUrl("http://" + appDomain + "/upload/" + fileName);
        return response;
    }

    @Override
    public void download(String filename, HttpServletResponse response) throws Exception {
        File target = new File(filename);

        // 设置返回消息的类型，并写入文件流
        response.reset();
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        try (ServletOutputStream out = response.getOutputStream(); InputStream inStream = new FileInputStream(target)) {
            byte[] b = new byte[1024];
            int len;
            while ((len = inStream.read(b)) > 0) {
                out.write(b, 0, len);
            }
            response.setStatus(HttpServletResponse.SC_OK);
            response.flushBuffer();
        }

    }

    private String getFileName(MultivaluedMap<String, String> header) {
        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
}
