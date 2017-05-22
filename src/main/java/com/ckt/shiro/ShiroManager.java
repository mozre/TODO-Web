package com.ckt.shiro;


import com.ckt.entity.User;
import com.ckt.service.UserService;
import com.ckt.utils.HTTPUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mozre on 2017/5/18.
 */

@Service
//声明这个service所有方法需要事务声明
@Transactional
public class ShiroManager extends AuthorizingRealm {

    //依赖注入
    @Resource
    private UserService userService;

    //权限认证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("jinru");
        String username = principalCollection.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        System.out.println(username + "===============");
        String per = userService.getPermissiin(username);
        Set<String> set = new HashSet<>();
        set.add(per);
        System.out.println(per + "------------------");
        String role = userService.getRole(username);
        Set<String> rol = new HashSet<>();
        set.add(role);
        System.out.println(role + "------------------------");
        info.setRoles(rol);
        info.setStringPermissions(set);
        return info;
    }

    //登录认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("首先进入");
        String email = authenticationToken.getPrincipal().toString();
        String password = new String((char[]) authenticationToken.getCredentials());
        System.out.println(email + "----------" + password);
        User user = new User();
        user.setMem_email(email);
        user.setMem_password(password);
        User us = userService.sele(user);
        userService.updateToken(us.getMem_id(), HTTPUtils.newToken());
        if (us != null) {
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getMem_email(), user.getMem_password(),
                    "a");

            return authenticationInfo;
        } else {
            return null;
        }

    }
}