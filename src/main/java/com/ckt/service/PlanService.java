package com.ckt.service;

import com.ckt.entity.Plan;

import java.util.List;

/**
 * Created by admin on 2017/6/1.
 */
public interface PlanService {

    void newPlan(Plan plan);
    List<Plan> getPlans(int id);
}
