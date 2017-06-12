package com.ckt.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ckt.dao.UserDao;
import com.ckt.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mozre on 2017/5/18.
 */

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User getUserById(int userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public List selectAll() {
        return null;
    }

    @Override
    public List selectLimit(Integer offset, Integer limit) {
        return null;
    }

    @Override
    public int update(int userId) {
        return 0;
    }

    @Override
    public int update2(User user) {
        return 0;
    }

    @Override
    public int delete(User user) {
        return 0;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public int somedel(Integer[] arr) {
        return 0;
    }

    @Override
    public List twosel() {
        return null;
    }

    @Override
    public User sele(User user) {
        return userDao.sele(user);
    }

    @Override
    public String getPermissiin(String name) {
        return userDao.getPermission(name);
    }

    @Override
    public String getRole(String name) {
        return userDao.getRole(name);
    }

    @Override
    public String getToken(int userId) {
        return userDao.getToken(userId);
    }

    @Override
    public void updateToken(int userId, String token) {
        userDao.updateToken(userId, token, System.currentTimeMillis());
    }

    @Override
    public User loginStatus(String email, String token) {
        return userDao.loginStatus(email, token);
    }

    @Override
    public User convertSignupData(String data) throws Exception {
        JSONObject json = JSON.parseObject(data);
//            User user = JSON.parseObject(json.getString("user"), User.class);
        String jsonStr = json.getString("user");
        JSONObject jsonData = JSON.parseObject(jsonStr);
        User user = new User();
        user.setMem_email(jsonData.getString("mem_email"));
        user.setMem_name(jsonData.getString("mem_name"));
        user.setMem_password(jsonData.getString("mem_password"));
        if (jsonData.containsKey("mem_phone")) {
            user.setMem_phone_num(jsonData.getString("mem_phone"));
        }
        if (jsonData.containsKey("mem_icon")) {
            user.setMem_icon(jsonData.getString("mem_icon"));
        } else {
            user.setMem_icon("aaaa");
        }
        if (jsonData.containsKey("mem_level")) {
            user.setMem_level(json.getInteger("mem_level"));
        } else {
            user.setMem_level(0);
        }

        return user;
    }

    @Override
    public Integer getMemId(String email) {
        return userDao.getMemId(email);
    }
}



