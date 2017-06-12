package com.ckt.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ckt.entity.Plan;
import com.ckt.entity.User;
import com.ckt.service.PlanService;
import com.ckt.service.UserService;
import com.ckt.utils.HttpConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by mozre on 2017/6/1.
 */

@Controller
public class PlanController {

    @Resource
    private UserService userService;
    @Resource
    private PlanService planService;

    @RequestMapping(value = "/project/plan", method = RequestMethod.POST)
    @ResponseBody
    public String newPlan(HttpServletRequest request, HttpServletResponse response) {
        JSONObject resultJson = new JSONObject();
        try {
            String email = request.getParameter("email");
            String token = request.getParameter("token");
            User user = userService.loginStatus(email, token);
            if (user == null) {
                resultJson.put(HttpConstant.RESLUT_CODE, 300);
            } else {
                Plan plan = new Plan();
                JSONObject dataJson = JSON.parseObject(request.getParameter("plan"));
                plan.setPlanID(dataJson.getString("plan_id"));
                plan.setMemID(dataJson.getInteger("mem_id"));
                plan.setProjectID(dataJson.getString("project_id"));
                plan.setPlanName(dataJson.getString("plan_name"));
                plan.setPlanDescrition(dataJson.getString("plan_description"));
                plan.setPlanStartTime(dataJson.getString("plan_start_time"));
                plan.setPlanEndTime(dataJson.getString("plan_end_time"));
                plan.setPlanCreateTime(dataJson.getString("plan_create_time"));
                plan.setPlanLastUpdateTime(String.valueOf(System.currentTimeMillis()));
                plan.setPlanState(dataJson.getInteger("plan_state"));
                plan.setPlanRealSpendTime(dataJson.getString("plan_real_spend_time"));
                plan.setSprint(dataJson.getInteger("sprint"));
                plan.setPlanAcomplishProgress(dataJson.getString("plan_acomplish_progress"));
                planService.newPlan(plan);
                resultJson.put(HttpConstant.RESLUT_CODE, 200);
            }
        } catch (Exception e) {
            resultJson.put(HttpConstant.RESLUT_CODE, 400);
            e.printStackTrace();
        }

        return resultJson.toJSONString();
    }

    @RequestMapping(value = "/project/plan", method = RequestMethod.GET)
    @ResponseBody
    public String getPlans(HttpServletRequest request, HttpServletResponse response) {
        JSONObject resultJson = new JSONObject();
        try {
            String email = request.getParameter("email");
            String token = request.getParameter("token");
            User user = userService.loginStatus(email, token);
            if (user == null) {
                resultJson.put(HttpConstant.RESLUT_CODE, 300);
            } else {
                int sprint = Integer.valueOf(request.getParameter("sprint"));
                int status = Integer.valueOf(request.getParameter("status"));
                List<Plan> planList = planService.getPlans(user.getMem_id(), sprint, status);
                resultJson.put("data", planList);
                resultJson.put(HttpConstant.RESLUT_CODE, 200);
            }
        } catch (Exception e) {
            resultJson.put(HttpConstant.RESLUT_CODE, 400);
            e.printStackTrace();
        }
        return resultJson.toJSONString();
    }

}
