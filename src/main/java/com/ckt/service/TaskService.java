package com.ckt.service;

import com.ckt.entity.Task;

import java.util.List;

/**
 * Created by mozre on 2017/6/2.
 */
public interface TaskService {

    void newTask(Task task);
    List<Task> getTasks(String planId);

    List<Task> getTaskById(Integer mem_id);
}
