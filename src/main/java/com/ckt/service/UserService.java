package com.ckt.service;


import com.ckt.entity.User;

import java.util.List;

/**
 * Created by mozre on 2017/5/18.
 */


public interface UserService {
    User getUserById(int userId);

    void insert(User user);

    List selectAll();

    List selectLimit(Integer offset, Integer limit);

    int update(int userId);

    int update2(User user);

    int delete(User user);

    int getCount();

    int somedel(Integer[] arr);

    List twosel();

    User sele(User user);

    String getPermissiin(String name);

    String getRole(String name);

    String getToken(int userId);

    void updateToken(int userId,String token);

    User loginStatus(String email,String token);

    User convertSignupData(String jsonStr) throws Exception;
    Integer getMemId(String email);
}
