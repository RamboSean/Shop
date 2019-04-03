package com.qf.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.qf.dao.impl.UserDaoImpl;
import com.qf.entity.User;

public class UserDaoTest {
	private IUserDao userDao = new UserDaoImpl();
	@Test
	public void testAddUser() {
		int result = userDao.addUser(new User(0, "fjj", "12345", "0", "138383883", "fj@163.com", "1"));
		System.out.println(result);
	}

	@Test
	public void testUpdateUser() {
		int result = userDao.updateUser(new User(99, "lyff", "12388", "0", "138383883", "fj@163.com", "1"));
		System.out.println(result);
	}

	@Test
	public void testDelUserById() {
		System.out.println(userDao.delUserById(98));
	}

	@Test
	public void testGetUserList() {
		List<User> list = userDao.getUserList();
		for(User user : list){
			System.out.println(user);
		}
	}

	@Test
	public void testGetUserById() {
		User user = userDao.getUserById(98);
		System.out.println(user);
	}
	
	@Test
	public void testTotalCount() {
		int count = userDao.getTotalCount();
		System.out.println(count);
	}
	
	@Test
	public void testGetUserListPage() {
		List<User> list = userDao.getUserListPage(3, 3);
		for(User user : list){
			System.out.println(user);
		}
	}

}
