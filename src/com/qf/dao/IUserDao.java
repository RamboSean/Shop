package com.qf.dao;

import java.util.List;

import com.qf.entity.User;

//用户接口，增删改查
public interface IUserDao {
	public int addUser(User user);  //增加
	
	public int updateUser(User user);  //修改
	
	public int delUserById(Integer id);   //删除
	
	public List<User> getUserList();   //查询
	
	public User getUserById(Integer id);  //查询单个对象
	
	public int getTotalCount();   //获取总条数

	public List<User> getUserListPage(Integer startIndex, Integer pageSize);

	public int batchDelIds(String[] ids);

	public User getUserByBackLogin(String name, String password);

	public User getUserByFrontLogin(String name, String password);
	
}
