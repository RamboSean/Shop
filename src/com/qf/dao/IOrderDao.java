package com.qf.dao;

import com.qf.entity.Order;

public interface IOrderDao {
	public int addOrder(Order order);

	public int addAndReturnPrimaryKey(Order order);
}
