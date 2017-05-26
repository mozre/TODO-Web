package com.ckt.service;

import com.ckt.entity.Project;

/**
 * Created by admin on 2017/5/25.
 */
public interface ProjectService {

    void newProject(Project project);
    Project getProject(String id);
}
