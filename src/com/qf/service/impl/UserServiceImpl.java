package com.qf.service.impl;

import java.util.List;

import com.qf.dao.IUserDao;
import com.qf.dao.impl.UserDaoImpl;
import com.qf.entity.Page;
import com.qf.entity.User;
import com.qf.service.IUserService;

public class UserServiceImpl implements IUserService {
	private IUserDao userDao = new UserDaoImpl();   //面向接口
	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public int delUserById(Integer id) {
		return userDao.delUserById(id);
	}

	@Override
	public List<User> getUserList() {
		return userDao.getUserList();
	}

	@Override
	public User getUserById(Integer id) {
		return userDao.getUserById(id);
	}

	@Override
	public Page getPage(String current) {
		Integer currentPage = 1;  //当前页默认为第一页
		Integer pageSize    = 3;  //每页显示3条
		if(current != null){
			currentPage = Integer.parseInt(current);
		}
		Page page = new Page(currentPage,pageSize);
		int totalCount = userDao.getTotalCount();
		page.setTotalCount(totalCount);  //设置总条数
		
		//页数量==总条数/页大小--如果整除就是该值，否则+1
		int pageCount = totalCount/pageSize;
		pageCount = totalCount%pageSize==0?pageCount:pageCount+1;
		page.setPageCount(pageCount);   //设置页数量
		
		Integer startIndex = (currentPage-1)*pageSize;  //起始下标为（当前页-1）*页大小
		//select * from t_user limit startIndex,pageSize
		List<User> list = userDao.getUserListPage(startIndex,pageSize);
		page.setList(list);   //设置数据
		page.setUrl("UserServlet?action=userList");
		return page;
	}

	@Override
	public int batchDelIds(String[] ids) {
		return userDao.batchDelIds(ids);
	}

	@Override
	public User getUserByBackLogin(String name, String password) {

		return userDao.getUserByBackLogin(name,password);
	}

	@Override
	public User getUserByFrontLogin(String name, String password) {
		return userDao.getUserByFrontLogin(name,password);
	}

}
