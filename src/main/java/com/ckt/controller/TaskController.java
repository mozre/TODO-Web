package com.ckt.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ckt.entity.Task;
import com.ckt.entity.User;
import com.ckt.service.TaskService;
import com.ckt.service.UserService;
import com.ckt.utils.HttpConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by mozre on 2017/6/2.
 */

@Controller
public class TaskController {

    @Resource
    private UserService userService;
    @Resource
    private TaskService taskService;

    @RequestMapping(value = "/project/task", method = RequestMethod.POST)
    @ResponseBody
    public String newTask(HttpServletRequest request, HttpServletResponse response) {
        JSONObject resultJson = new JSONObject();
        try {
            String email = request.getParameter("email");
            String token = request.getParameter("token");
            User user = userService.loginStatus(email, token);
            if (user == null) {
                resultJson.put(HttpConstant.RESLUT_CODE, 300);
            } else {
                Task task = new Task();
                JSONObject dataJson = JSON.parseObject(request.getParameter("task"));
                task.setPlanId(dataJson.getString("plan_id"));
                task.setMem_id(dataJson.getInteger("men_id"));
                task.setTaskId(dataJson.getString("task_id"));
                task.setTaskTitle(dataJson.getString("task_title"));
                task.setTaskContent(dataJson.getString("task_content"));
                task.setTaskType(dataJson.getInteger("task_type"));
                task.setTaskPriority(dataJson.getInteger("task_prioirty"));
                task.setTaskStatus(dataJson.getInteger("task_status"));
                task.setTaskStartTime(dataJson.getString("task_start_time"));
                task.setTaskPredictTime(dataJson.getString("task_pridict_time"));
                task.setTaskRemindTime(dataJson.getString("task_remind_time"));
                task.setTaskRealSpendTime(dataJson.getString("task_real_spend_time"));
                task.setTaskUpdateTime(String.valueOf(System.currentTimeMillis()));
                taskService.newTask(task);
                resultJson.put(HttpConstant.RESLUT_CODE, 200);
            }
        } catch (Exception e) {
            resultJson.put(HttpConstant.RESLUT_CODE, 400);
            e.printStackTrace();
        }

        return resultJson.toJSONString();
    }

    @RequestMapping(value = "/project/task", method = RequestMethod.GET)
    @ResponseBody
    public String getTaskById(HttpServletRequest request, HttpServletResponse response) {
        JSONObject resultJson = new JSONObject();
        try {
            String email = request.getParameter("email");
            String token = request.getParameter("token");
            User user = userService.loginStatus(email, token);
            if (user == null) {
                resultJson.put(HttpConstant.RESLUT_CODE, 300);
            } else {
                List<Task> taskList = taskService.getTaskById(user.getMem_id());
                if (taskList == null) {
                    resultJson.put(HttpConstant.RESLUT_CODE, 300);
                } else {
                    resultJson.put("data", taskList);
                    resultJson.put(HttpConstant.RESLUT_CODE, 200);
                }
            }
        } catch (Exception e) {
            resultJson.put(HttpConstant.RESLUT_CODE, 400);
            e.printStackTrace();
        }

        return resultJson.toJSONString();
    }

    @RequestMapping(value = "/project/plan/task", method = RequestMethod.GET)
    @ResponseBody
    public String getTasks(HttpServletRequest request, HttpServletResponse response) {
        JSONObject resultJson = new JSONObject();

        try {
            String email = request.getParameter("email");
            String token = request.getParameter("token");
            User user = userService.loginStatus(email, token);
            if (user == null) {
                resultJson.put(HttpConstant.RESLUT_CODE, 300);
            } else {
                String planId = (request.getParameter("planid"));
                List<Task> taskList = taskService.getTasks(planId);
                if (taskList == null) {
                    resultJson.put(HttpConstant.RESLUT_CODE, 300);
                } else {
                    resultJson.put("data", taskList);
                    resultJson.put(HttpConstant.RESLUT_CODE, 200);
                }
            }
        } catch (Exception e) {
            resultJson.put(HttpConstant.RESLUT_CODE, 400);
            e.printStackTrace();
        }

        return resultJson.toJSONString();
    }


}
