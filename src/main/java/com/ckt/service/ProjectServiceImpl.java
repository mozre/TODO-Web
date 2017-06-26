package com.ckt.service;

import com.alibaba.fastjson.JSONObject;
import com.ckt.dao.ProjectDao;
import com.ckt.entity.Project;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/5/25.
 */

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectDao projectDao;

    @Override
    public void newProject(Project project) throws Exception {
        projectDao.insertProject(project);
    }

    @Override
    public Project getProject(String id) throws Exception {
        return projectDao.getProject(id);
    }

    @Override
    public List<Project> getProjects(Integer mem_id) throws Exception {
        return projectDao.getProjects(mem_id);
    }

    @Override
    public void deleteProject(String id) throws Exception {
        projectDao.deleteProject(id);
    }

    @Override
    public void modifyProject(Project project) throws Exception {
        projectDao.modifyProject(project);
    }

    @Override
    public Integer selectSprint(String projectId) throws Exception {
        return projectDao.selectSprint(projectId);
    }

    @Override
    public void newSprint(int sprint, String projectId) {
        projectDao.newSprint(sprint,projectId);
    }

    @Override
    public void sceenProject(List<Project> data) {
        for (Project project : data) {
            if (project.getProjectVisibility() == 0) {
                data.remove(project);
            }
        }
    }

    @Override
    public void convertProject(JSONObject data, Project project) throws Exception {
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
    }
}
