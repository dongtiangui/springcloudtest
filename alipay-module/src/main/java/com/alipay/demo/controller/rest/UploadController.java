package com.alipay.demo.controller.rest;

import com.alipay.demo.pojo.UploadResponse;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/upload")
public interface UploadController {

    /**
     * 上传图片到应用根目录
     * @param request
     * @param formData
     * @return 返回消息，其中imgUrl为图片的访问url路径
     * @throws Exception
     */
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    UploadResponse upload(@Context HttpServletRequest request, MultipartFormDataInput formData) throws Exception;

    /**
     * 下载图片
     * @param filename 文件名
     * @param response 返回消息，用于写入文件流
     * @throws Exception
     */
    @GET
    @Path("/{filename}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    void download(@PathParam("filename") String filename, @Context HttpServletResponse response) throws Exception;

}
