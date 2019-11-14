package com.alipay.demo.dal;

import com.alipay.demo.pojo.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
    Iterable<Task> findByUserId(String userId);
}
