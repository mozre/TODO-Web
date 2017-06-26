package com.ckt.service;

import com.alibaba.fastjson.JSONObject;
import com.ckt.entity.Project;

import java.util.List;

/**
 * Created by admin on 2017/5/25.
 */
public interface ProjectService {

    void newProject(Project project) throws Exception;

    Project getProject(String id) throws Exception;

    List<Project> getProjects(Integer mem_id) throws Exception;

    void sceenProject(List<Project> data) throws Exception;

    void deleteProject(String id) throws Exception;

    void convertProject(JSONObject data, Project project) throws Exception;

    void modifyProject(Project project) throws Exception;

    Integer selectSprint(String projectId) throws Exception;

    void newSprint(int sprint, String projectId);
}
