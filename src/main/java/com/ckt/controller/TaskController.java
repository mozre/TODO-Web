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
                Task task = taskService.convertTask(request.getParameter("task"));
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

    @RequestMapping(value = "project/plan/task/modify")
    @ResponseBody
    public String modifyTask(HttpServletRequest request, HttpServletResponse response) {
        JSONObject resultJson = new JSONObject();
        try {
            String email = request.getParameter("email");
            String token = request.getParameter("token");
            User user = userService.loginStatus(email, token);
            if (user == null) {
                resultJson.put(HttpConstant.RESLUT_CODE, 300);
            } else {
                Task task = taskService.convertTask(request.getParameter("task"));
                Task exTask = taskService.getTask(task.getTaskId());
                if(exTask ==null){
                    resultJson.put(HttpConstant.RESLUT_CODE, 400);
                }else {
                    taskService.modifyTask(task);
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
