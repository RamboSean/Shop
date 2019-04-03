package com.qf.service.impl;

import com.qf.dao.IOrderDao;
import com.qf.dao.impl.OrderDaoImpl;
import com.qf.entity.Order;
import com.qf.service.IOrderService;

public class OrderServiceImpl implements IOrderService {

	private IOrderDao orderDao = new OrderDaoImpl();
	@Override
	public int addOrder(Order order) {
		return orderDao.addOrder(order);
	}
	@Override
	public int addAndReturnPrimaryKey(Order order) {
		
		return orderDao.addAndReturnPrimaryKey(order);
	}

}
