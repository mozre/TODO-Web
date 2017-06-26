package com.ckt.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

    @Override
    public Task getTask(String taskId) throws Exception {

        return taskDao.getTask(taskId);
    }

    @Override
    public void modifyTask(Task task) throws Exception {
        taskDao.modifyTask(task);
    }

    @Override
    public Task convertTask(String jsonStr) throws Exception {
        JSONObject dataJson = JSON.parseObject(jsonStr);
        Task task = new Task();
        task.setPlanId(dataJson.getString("plan_id"));
        task.setMem_id(dataJson.getInteger("men_id"));
        task.setTaskId(dataJson.getString("task_id"));
        task.setTaskTitle(dataJson.getString("task_title"));
        if (dataJson.containsKey("task_content")) {
            task.setTaskContent(dataJson.getString("task_content"));
        }
        task.setTaskType(dataJson.getInteger("task_type"));
        task.setTaskPriority(dataJson.getInteger("task_prioirty"));
        task.setTaskStatus(dataJson.getInteger("task_status"));
        task.setTaskStartTime(dataJson.getString("task_start_time"));
        task.setTaskPredictTime(dataJson.getString("task_pridict_time"));
        task.setTaskRemindTime(dataJson.getString("task_remind_time"));
        task.setTaskRealSpendTime(dataJson.getString("task_real_spend_time"));
        task.setTaskUpdateTime(String.valueOf(System.currentTimeMillis()));
        return null;
    }
}
