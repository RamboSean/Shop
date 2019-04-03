package com.qf.service;

import java.util.List;

import com.qf.entity.Page;
import com.qf.entity.User;
//业务层
public interface IUserService {
	public int addUser(User user);  //增加
	
	public int updateUser(User user);  //修改
	
	public int delUserById(Integer id);   //删除
	
	public List<User> getUserList();   //查询
	
	public User getUserById(Integer id);  //查询单个对象

	public Page getPage(String current);

	public int batchDelIds(String[] ids);

	public User getUserByBackLogin(String name, String password);

	public User getUserByFrontLogin(String name, String password);
}
