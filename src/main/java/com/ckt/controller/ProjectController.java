package com.ckt.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ckt.entity.Project;
import com.ckt.entity.User;
import com.ckt.service.ProjectService;
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
                    if (data.containsKey("project_description")) {
                        project.setProjectSummary(data.getString("project_description"));
                    } else {
                        project.setProjectSummary("");
                    }
                    if (data.containsKey("project_end_time")) {
                        project.setEndTime(data.getString("project_end_time"));
                    } else {
                        project.setEndTime("");
                    }
                    project.setCreateTime(data.getString("project_create_time"));
                    if (data.containsKey("project_acomplish_progress")) {
                        project.setAccomplishProgress(data.getString("project_acomplish_progress"));
                    } else {
                        project.setAccomplishProgress("0");
                    }
                    if (data.containsKey("project_sprint_count")) {
                        project.setSprint(data.getInteger("project_sprint_count"));
                    } else {
                        project.setSprint(1);
                    }
                    if (data.containsKey("team_id")) {
                        project.setTeamId(data.getInteger("team_id"));
                    } else {
                        project.setTeamId(1);
                    }
                    if (data.containsKey("project_visibility")) {
                        project.setProjectVisibility(data.getInteger("project_visibility"));
                    } else {
                        project.setProjectVisibility(0);
                    }
                    project.setLastUpdateTime(String.valueOf(System.currentTimeMillis()));
                    projectService.newProject(project);
                    object.put("resultcode", 200);
                }
            } else {
                object.put("resultcode", 300);
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            object.put("resultcode", 500);

        } catch (Exception e) {
            e.printStackTrace();
            object.put("resultcode", 600);
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
            String targetEmail = request.getParameter("target_email");
            User user = userService.loginStatus(email, token);
            if (user == null) {
                resultJson.put(HttpConstant.RESLUT_CODE, 300);
            } else {
                Integer targetId = userService.getMemId(targetEmail);
                if (targetId == null) {
                    resultJson.put(HttpConstant.RESLUT_CODE, 400);
                } else {
                    List<Project> projects = projectService.getProjects(targetId);
                    if (targetId != user.getMem_id()) {
                        projectService.sceenProject(projects);
                    }
                    resultJson.put("data", projects);
                    resultJson.put(HttpConstant.RESLUT_CODE, 200);
                }

            }
        } catch (Exception e) {
            resultJson.put(HttpConstant.RESLUT_CODE, 400);
            e.printStackTrace();
        }


        return resultJson.toJSONString();
    }

    @RequestMapping(value = "/project/delete", method = RequestMethod.GET)
    @ResponseBody
    public String deleteProject(HttpServletRequest request,HttpServletResponse response){
        JSONObject resultJson = new JSONObject();
        try {
            String email = request.getParameter("email");
            String token = request.getParameter("token");
            User user = userService.loginStatus(email, token);
            if (user == null) {
                resultJson.put(HttpConstant.RESLUT_CODE, 300);
            } else {
                String projectId = request.getParameter("project_id");
                Project project = projectService.getProject(projectId);
                if (project != null) {
                    projectService.deleteProject(projectId);
                }
                resultJson.put(HttpConstant.RESLUT_CODE, 200);
            }
        } catch (Exception e) {
            resultJson.put(HttpConstant.RESLUT_CODE, 400);
            e.printStackTrace();
        }

        return resultJson.toJSONString();
    }
}
