package com.ckt.dao;

import com.ckt.entity.Task;
import com.ckt.utils.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mozre on 2017/6/2.
 */

@MyBatisDao
public interface TaskDao {


    void newTask(Task task);

    List<Task> getTasks(@Param("planId") String planId);

    List<Task> getTaskById(@Param("mem_id") Integer mem_id);
}
