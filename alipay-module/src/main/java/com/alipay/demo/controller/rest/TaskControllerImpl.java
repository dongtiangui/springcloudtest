package com.alipay.demo.controller.rest;

import com.alipay.demo.pojo.GetTodosResponse;
import com.alipay.demo.pojo.IdRequest;
import com.alipay.demo.pojo.SuccessResponse;
import com.alipay.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TaskControllerImpl implements TaskController {
    @Autowired
    TaskService taskService;

    @Override
    public GetTodosResponse getTodos(String userId) throws Exception {
        GetTodosResponse res = new GetTodosResponse();
        res.setSuccess(true);
        if (userId == null || userId.equals("")) {
            res.setTodoList(taskService.getAllTasks());
        } else {
            res.setTodoList(taskService.getByUserId(userId));
        }
        return res;
    }

    @Override
    public SuccessResponse addTodo(com.alipay.demo.pojo.Task task) throws Exception {
        taskService.insert(task);
        return new SuccessResponse(true);
    }

    @Override
    public SuccessResponse deleteTodo(IdRequest req) throws Exception {
        Long taskId = req.getId();
        taskService.deleteById(taskId);
        return new SuccessResponse(true);
    }

    @Override
    public SuccessResponse changeState(IdRequest req) throws Exception {
        Long taskId = req.getId();
        taskService.toggleState(taskId);
        return new SuccessResponse(true);
    }

}
