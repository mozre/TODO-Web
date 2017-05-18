package com.ckt.service;



import com.ckt.dao.UserDao;
import com.ckt.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mozre on 2017/5/18.
 */

@Service("userService")
public class UserImpl implements UserService {
	@Resource
	private UserDao userDao;
	@Override
	public User getUserById(int userId) {
		return userDao.selectByPrimaryKey(userId);
	}

	@Override
	public int insert(User user) {
		return 0;
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
	public User sele(User user)  {
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
}
		


