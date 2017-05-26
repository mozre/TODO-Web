package com.ckt.service;

import com.ckt.dao.ProjectDao;
import com.ckt.entity.Project;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
