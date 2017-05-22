package com.ckt.dao;

import com.ckt.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mozre on 2017/5/18.
 */


public interface UserDao {

    void insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(@Param("id") int id);

    List selectAll();

    List twosel();

    List selectLimit(@Param("offset") Integer offset, @Param("limit") Integer limit);

    int update(int userId);

    int update2(User user);

    int delete(User user);

    int getCount();

    int somedel(Integer[] arr);

    void traninsert(User users);

    User sele(User user);

    String getPermission(@Param("name") String username);

    String getRole(@Param("name") String username);

    String getToken(@Param("mem_id") int id);

    void updateToken(@Param("mem_id") int id, @Param("token") String token, @Param("token_create_time") long token_create_time);

    User loginStatus(@Param("mem_email") String email, @Param("token") String token);
}