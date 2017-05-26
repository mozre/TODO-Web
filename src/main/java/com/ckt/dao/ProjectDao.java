package com.ckt.dao;

import com.ckt.entity.Project;
import com.ckt.utils.MyBatisDao;
import org.apache.ibatis.annotations.Param;

/**
 * Created by mozre on 2017/5/25.
 */

@MyBatisDao
public interface ProjectDao {

    void insertProject(Project project);
    Project getProject(@Param("projectId") String id);
}
