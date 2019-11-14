package com.alipay.demo.service;

import com.alipay.demo.dal.TaskRepository;
import com.alipay.demo.pojo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author leian.la
 * @date 12/12/2018 8:30 PM
 */
@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.delete(id);
    }

    @Override
    public void insert(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void toggleState(Long id) {
        Task task = taskRepository.findOne(id);
        task.setCompleted(!task.getCompleted());
        taskRepository.save(task);
    }

    @Override
    public Iterable<Task> getByUserId(String userId) {
        return taskRepository.findByUserId(userId);
    }
}
