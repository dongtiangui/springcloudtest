package com.alipay.demo.controller.rest;

import com.alipay.demo.config.RestConstants;
import com.alipay.demo.pojo.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/todos")
@Consumes(RestConstants.DEFAULT_CONTENT_TYPE)
@Produces(RestConstants.DEFAULT_CONTENT_TYPE)
public interface TaskController {
    /**
     * 获取所有的todo项
     * @param userId 用户ID（当前并未使用）
     * @return 返回消息，其中success属性表示请求结果
     * @throws Exception
     */
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    GetTodosResponse getTodos(@QueryParam("userId") String userId) throws Exception;

    /**
     * 添加一个todo项
     * @param task 任务信息
     * @return 返回消息，其中success属性表示请求结果
     * @throws Exception
     */
    @POST
    @Path("/add")
    SuccessResponse addTodo(Task task) throws Exception;

    /**
     * 删除一个todo项
     * @param req 请求消息，其中含有删除项的ID
     * @return 返回消息，其中success属性表示请求结果
     * @throws Exception
     */
    @POST
    @Path("/delete")
    SuccessResponse deleteTodo(IdRequest req) throws Exception;

    /**
     * 修改todo项的完成状态
     * @param req 请求消息
     * @return 返回消息，其中success属性表示请求结果
     * @throws Exception
     */
    @POST
    @Path("/change")
    SuccessResponse changeState(IdRequest req) throws Exception;

}
