package com.qf.service.impl;

import java.util.List;

import com.qf.dao.IOrderDetailDao;
import com.qf.dao.impl.OrderDetailImpl;
import com.qf.entity.OrderDetail;
import com.qf.service.IOrderDetailService;

public class OrderDetailServiceImpl implements IOrderDetailService {

	private IOrderDetailDao orderDetailDao = new OrderDetailImpl();
	@Override  //添加订单明细
	public int addOrderDetail(OrderDetail detail) {
		
		return orderDetailDao.addOrderDetail(detail);
	}
	@Override
	public void batchaddOrderDetail(List<OrderDetail> list) {
		orderDetailDao.batchaddOrderDetail(list);
	}
	
}
