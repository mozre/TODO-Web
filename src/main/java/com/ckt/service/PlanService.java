package com.ckt.service;

import com.ckt.entity.Plan;

import java.util.List;

/**
 * Created by admin on 2017/6/1.
 */
public interface PlanService {

    void newPlan(Plan plan) throws Exception;

    List<Plan> getPlans(int mem_id, int sprint, int status) throws Exception;

    void deletePlan(String planId) throws Exception;

    Plan selectPlan(String planId) throws Exception;

    Plan convertPlan(String jsonString) throws Exception;

    void modifyPlan(Plan plan) throws Exception;
}
