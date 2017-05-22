package com.ckt.controller;

import com.ckt.entity.User;
import com.ckt.service.UserService;
import com.ckt.utils.HTTPUtils;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by admin on 2017/5/19.
 */

@Controller
public class LogoutController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        String token = request.getParameter("token");
        try {
            User user = userService.loginStatus(email, token);
            int id = user.getMem_id();
            userService.updateToken(user.getMem_id(), HTTPUtils.newToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
