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

     List<Plan> getPlans(@Param("memID") Integer id,@Param("sprint") int sprint, @Param("planState") int status);

     void deletePlan(@Param("planID") String planId);

     Plan selectPlan(@Param("planID") String planId);

     void modifyPlan(Plan plan);
}
