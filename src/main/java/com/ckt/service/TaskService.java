package com.ckt.service;

import com.ckt.entity.Task;

import java.util.List;

/**
 * Created by mozre on 2017/6/2.
 */
public interface TaskService {

    void newTask(Task task) throws Exception;

    List<Task> getTasks(String planId) throws Exception;

    List<Task> getTaskById(Integer mem_id) throws Exception;

    Task getTask(String taskId) throws Exception;

    Task convertTask(String jsonStr) throws Exception;

    void modifyTask(Task task) throws Exception;
}
