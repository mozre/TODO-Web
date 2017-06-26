package com.ckt.dao;

import com.ckt.entity.Project;
import com.ckt.utils.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mozre on 2017/5/25.
 */

@MyBatisDao
public interface ProjectDao {

    void insertProject(Project project);

    Project getProject(@Param("projectId") String id);

    List<Project> getProjects(@Param("memId") Integer mem_id);

    void deleteProject(@Param("projectId") String id);

    void modifyProject(Project project);

    Integer selectSprint(@Param("projectId") String projectId);

    void newSprint(@Param("sprint") int sprint, @Param("projectId") String projectId);
}
