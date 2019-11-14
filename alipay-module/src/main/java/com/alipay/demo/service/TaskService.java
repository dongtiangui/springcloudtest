package com.alipay.demo.service;

import com.alipay.demo.pojo.Task;

/**
 * @author leian.la
 * @date 12/12/2018 8:30 PM
 */
public interface TaskService {

    Iterable<Task> getAllTasks();

    void deleteById(Long id);


    void insert(Task task);

    void toggleState(Long id);

    Iterable<Task> getByUserId(String userId);
}
