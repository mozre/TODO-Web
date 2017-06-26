package com.ckt.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ckt.dao.PlanDao;
import com.ckt.entity.Plan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mozre on 2017/6/1.
 */

@Service("planService")
public class PlanServiceImpl implements PlanService {

    @Resource
    private PlanDao planDao;

    @Override
    public void newPlan(Plan plan) throws Exception {
        planDao.newPlan(plan);
    }

    @Override
    public List<Plan> getPlans(int mem_id, int sprint, int status) throws Exception {
        return planDao.getPlans(mem_id, sprint, status);
    }

    @Override
    public void deletePlan(String planId) throws Exception {
        planDao.deletePlan(planId);
    }

    @Override
    public Plan selectPlan(String planId) throws Exception {
        return planDao.selectPlan(planId);
    }

    @Override
    public void modifyPlan(Plan plan) throws Exception {
        planDao.modifyPlan(plan);
    }

    @Override
    public Plan convertPlan(String jsonString) throws Exception {
        JSONObject dataJson = JSON.parseObject(jsonString);
        Plan plan = new Plan();
        plan.setPlanID(dataJson.getString("plan_id"));
        plan.setMemID(dataJson.getInteger("mem_id"));
        plan.setProjectID(dataJson.getString("project_id"));
        plan.setPlanName(dataJson.getString("plan_name"));
        if (dataJson.containsKey("plan_description")) {
            plan.setPlanDescrition(dataJson.getString("plan_description"));
        }
        if (dataJson.containsKey("plan_start_time")) {
            plan.setPlanDescrition(dataJson.getString("plan_start_time"));
        }
        if (dataJson.containsKey("plan_end_time")) {
            plan.setPlanEndTime(dataJson.getString("plan_end_time"));
        }
        if (dataJson.containsKey("plan_create_time")) {
            plan.setPlanCreateTime(dataJson.getString("plan_create_time"));
        }
        plan.setPlanLastUpdateTime(String.valueOf(System.currentTimeMillis()));
        plan.setPlanState(dataJson.getInteger("plan_state"));
        if (dataJson.containsKey("plan_real_spend_time")) {
            plan.setPlanRealSpendTime(dataJson.getString("plan_real_spend_time"));
        }
        plan.setSprint(dataJson.getInteger("sprint"));
        if (dataJson.containsKey("plan_acomplish_progress")) {
            plan.setPlanAcomplishProgress(dataJson.getString("plan_acomplish_progress"));
        }
        return plan;
    }
}
