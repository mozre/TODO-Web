package com.ckt.service;

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
    public void newPlan(Plan plan) {
        planDao.newPlan(plan);
    }

    @Override
    public List<Plan> getPlans(int mem_id, int sprint, int status) {
        return planDao.getPlans(mem_id,sprint,status);
    }
}
