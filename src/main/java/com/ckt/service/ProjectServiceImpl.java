package com.ckt.service;

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
    public void newProject(Project project) {
        projectDao.insertProject(project);
    }

    @Override
    public Project getProject(String id) {
        return projectDao.getProject(id);
    }

    @Override
    public List<Project> getProjects(Integer mem_id) {
        return projectDao.getProjects(mem_id);
    }

    @Override
    public void deleteProject(String id) {
        projectDao.deleteProject(id);
    }

    @Override
    public void sceenProject(List<Project> data) {
        for (Project project : data) {
            if (project.getProjectVisibility() == 0) {
                data.remove(project);
            }
        }
    }
}
