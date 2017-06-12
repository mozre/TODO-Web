package com.ckt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ckt.entity.User;
import com.ckt.service.UserService;
import com.ckt.utils.HttpConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mozre on 2017/5/18.
 */

@Controller
public class LoginController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonResult = new JSONObject();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UsernamePasswordToken token = new UsernamePasswordToken(email, password);
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            System.out.println("对用户[" + email + "]进行登录验证..验证未通过,未知账户");
            request.setAttribute("message_login", "未知账户");
        } catch (IncorrectCredentialsException ice) {
            System.out.println("对用户[" + email + "]进行登录验证..验证未通过,错误的凭证");
            request.setAttribute("message_login", "密码不正确");
        } catch (LockedAccountException lae) {
            System.out.println("对用户[" + email + "]进行登录验证..验证未通过,账户已锁定");
            request.setAttribute("message_login", "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            System.out.println("对用户[" + email + "]进行登录验证..验证未通过,错误次数过多");
            request.setAttribute("message_login", "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            System.out.println("对用户[" + email + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            request.setAttribute("message_login", "用户名或密码不正确");
        }
        //验证是否登录成功
        if (currentUser.isAuthenticated()) {
            User user = new User();
            user.setMem_email(email);
            user.setMem_password(password);
            User us = userService.sele(user);
            String uToken = userService.getToken(us.getMem_id());
            jsonResult.put(HttpConstant.RESLUT_CODE, 200);
            jsonResult.put("data", us);
            jsonResult.put("token", uToken);
            System.out.println("用户[" + email + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
        } else {
            token.clear();
            jsonResult.put("resultcode", 400);
        }

        return jsonResult.toJSONString();
    }


}
