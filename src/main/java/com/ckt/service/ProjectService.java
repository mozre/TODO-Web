package com.ckt.service;

import com.ckt.entity.Project;

import java.util.List;

/**
 * Created by admin on 2017/5/25.
 */
public interface ProjectService {

    void newProject(Project project);

    Project getProject(String id);

    List<Project> getProjects(Integer mem_id);

    void sceenProject(List<Project> data);

    void deleteProject(String id);
}
