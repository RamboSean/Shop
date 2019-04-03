package com.qf.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.qf.dao.impl.OrderDaoImpl;
import com.qf.entity.Order;

public class OrderDaoTest {
	private IOrderDao orderDao = new OrderDaoImpl();
	@Test
	public void testAddOrder() {
		int result = orderDao.addOrder(new Order(0, "通通", "连连", 555.5, null, 37, "aaa", "1366666", "xxxxxx"));
		System.out.println(result);
	}
	
	@Test
	public void testAddOrderPrimaryKey() {
		int result = orderDao.addAndReturnPrimaryKey(new Order(0, "通通1", "连连1", 555.5, null, 37, "aaa", "1366666", "xxxxxx"));
		System.out.println(result);
	}

}
