package com.ckt.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ckt.entity.User;
import com.ckt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping(value = "/user")
    @ResponseBody
    public String signup(HttpServletRequest request, HttpServletResponse response) {

        String data = request.getParameter("data");
        JSONObject object = new JSONObject();
        try {
            JSONObject json = JSON.parseObject(data);
            User user = JSON.parseObject(json.getString("user"), User.class);
            user.setMem_level(1);
            user.setMem_icon("aaaa");
            User user1 = userService.sele(user);
            if (user1 == null) {
                userService.insert(user);
                object.put("resultcode", 200);
            } else {
                object.put("resultcode", 300);
            }
        } catch (Exception e) {
            object.put("resultcode", 400);
            e.printStackTrace();
        }

        return object.toJSONString();
    }


}
