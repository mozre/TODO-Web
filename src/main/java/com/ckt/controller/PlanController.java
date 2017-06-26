package com.ckt.controller;

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
                Plan plan = planService.convertPlan(request.getParameter("plan"));
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

    @RequestMapping(value = "project/plan/delete", method = RequestMethod.GET)
    @ResponseBody
    public String delPlan(HttpServletRequest request, HttpServletResponse response) {
        JSONObject resultJson = new JSONObject();
        try {
            String email = request.getParameter("email");
            String token = request.getParameter("token");
            User user = userService.loginStatus(email, token);
            if (user == null) {
                resultJson.put(HttpConstant.RESLUT_CODE, 300);
            } else {
                String planId = request.getParameter("plan_id");
                Plan plan = planService.selectPlan(planId);
                if (plan != null) {
                    planService.deletePlan(planId);
                }
                resultJson.put(HttpConstant.RESLUT_CODE, 200);
            }
        } catch (Exception e) {
            resultJson.put(HttpConstant.RESLUT_CODE, 400);
            e.printStackTrace();
        }
        return resultJson.toJSONString();
    }

    @RequestMapping(value = "project/plan/modify", method = RequestMethod.POST)
    @ResponseBody
    public String modifyPlan(HttpServletRequest request, HttpServletResponse response) {
        JSONObject resultJson = new JSONObject();
        try {
            String email = request.getParameter("email");
            String token = request.getParameter("token");
            User user = userService.loginStatus(email, token);
            if (user == null) {
                resultJson.put(HttpConstant.RESLUT_CODE, 300);
            } else {
                Plan plan = planService.convertPlan(request.getParameter("plan"));
                Plan selectPlan = planService.selectPlan(plan.getPlanID());
                if (selectPlan != null) {
                    planService.modifyPlan(plan);
                    resultJson.put(HttpConstant.RESLUT_CODE, 200);
                } else {
                    resultJson.put(HttpConstant.RESLUT_CODE, 400);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultJson.put(HttpConstant.RESLUT_CODE, 400);
        }

        return resultJson.toJSONString();
    }

//    @RequestMapping(value = "project/plan/modify", method = RequestMethod.GET)
//    @ResponseBody
//    public String changePlanStatus()
}
