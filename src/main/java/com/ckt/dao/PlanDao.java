package com.ckt.dao;

import com.ckt.entity.Plan;
import com.ckt.utils.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mozre on 2017/6/1.
 */

@MyBatisDao
public interface PlanDao {

     void newPlan(Plan plan);

     List<Plan> getPlans(@Param("memID") Integer id);
}
