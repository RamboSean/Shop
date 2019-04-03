package com.qf.service;

import com.qf.entity.Order;

public interface IOrderService {
	public int addOrder(Order order);

	public int addAndReturnPrimaryKey(Order order);
}
