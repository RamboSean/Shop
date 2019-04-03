package com.qf.dao;

import java.util.List;

import com.qf.entity.OrderDetail;

public interface IOrderDetailDao {
	public int addOrderDetail(OrderDetail detail);

	public void batchaddOrderDetail(List<OrderDetail> list);
}
