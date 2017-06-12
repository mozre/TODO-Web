package com.ckt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ckt.entity.User;
import com.ckt.service.UserService;
import com.ckt.utils.HttpConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mozre on 2017/5/19.
 */


@Controller
public class SignupController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public String signup(HttpServletRequest request, HttpServletResponse response) {

        String data = request.getParameter("data");
        JSONObject object = new JSONObject();
        try {
            User user = userService.convertSignupData(data);
            User user1 = userService.sele(user);
            if (user1 == null) {
                userService.insert(user);
                object.put(HttpConstant.RESLUT_CODE, 200);
            } else {
                object.put(HttpConstant.RESLUT_CODE, 300);
            }
        } catch (Exception e) {
            object.put(HttpConstant.RESLUT_CODE, 400);
            e.printStackTrace();
        }

        return object.toJSONString();
    }


}
