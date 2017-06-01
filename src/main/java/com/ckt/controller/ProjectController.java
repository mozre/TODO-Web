package com.ckt.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ckt.entity.Project;
import com.ckt.entity.User;
import com.ckt.service.ProjectService;
import com.ckt.service.UserService;
import com.ckt.utils.HTTPConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by mozre on 2017/5/25.
 */


@Controller
public class ProjectController {

    @Resource
    private UserService userService;
    @Resource
    private ProjectService projectService;

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    @ResponseBody
    public String newProject(HttpServletRequest request, HttpServletResponse response) {

        JSONObject object = new JSONObject();
        try {
            String email = request.getParameter("email");
            String token = request.getParameter("token");
            User user = userService.loginStatus(email, token);
            if (user != null) {
                Project project = new Project();
                JSONObject data = JSON.parseObject(request.getParameter("postproject"));
                project.setProjectId(data.getString("project_id"));
                Project exPro = projectService.getProject(data.getString("project_id"));
                if (exPro != null) {
                    object.put("resultcode", 400);
                } else {
                    project.setMemId(data.getInteger("mem_id"));
                    project.setProjectTitle(data.getString("project_name"));
                    project.setProjectSummary(data.getString("project_description"));
                    project.setEndTime(data.getString("project_end_time"));
                    project.setCreateTime(data.getString("project_create_time"));
                    project.setAccomplishProgress(data.getString("project_acomplish_progress"));
                    project.setSprint(data.getInteger("project_sprint_count"));
                    project.setTeamId(data.getInteger("team_id"));
                    project.setLastUpdateTime(String.valueOf(System.currentTimeMillis()));
                    projectService.newProject(project);
                    object.put("resultcode", 200);
                }
            } else {
                object.put("resultcode", 300);
            }

        } catch (NullPointerException e) {


        } catch (Exception e) {

        }

        return object.toJSONString();
    }


    @RequestMapping(value = "/project", method = RequestMethod.GET)
    @ResponseBody
    public String getProjects(HttpServletRequest request, HttpServletResponse response) {
        JSONObject resultJson = new JSONObject();
        try {
            String email = request.getParameter("email");
            String token = request.getParameter("token");
            User user = userService.loginStatus(email, token);
            if (user == null) {
                resultJson.put(HTTPConstant.RESLUT_CODE, 300);
            } else {
                List<Project> projects = projectService.getProjects(user.getMem_id());
                resultJson.put(HTTPConstant.RESLUT_CODE, 200);
                resultJson.put("data", projects);
            }
        } catch (Exception e) {
            resultJson.put(HTTPConstant.RESLUT_CODE, 400);
            e.printStackTrace();
        }


        return resultJson.toJSONString();
    }

}
