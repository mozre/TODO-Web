package com.ckt.service;

import com.ckt.dao.TaskDao;
import com.ckt.entity.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/6/2.
 */

@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskDao taskDao;

    @Override
    public void newTask(Task task) {
        taskDao.newTask(task);
    }

    @Override
    public List<Task> getTasks(String planId) {
        return taskDao.getTasks(planId);
    }

    @Override
    public List<Task> getTaskById(Integer mem_id) {
        return taskDao.getTaskById(mem_id);
    }
}
