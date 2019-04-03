package com.qf.service;

import java.util.List;

import com.qf.entity.OrderDetail;

public interface IOrderDetailService {
	public int addOrderDetail(OrderDetail detail);

	public void batchaddOrderDetail(List<OrderDetail> list);
}
