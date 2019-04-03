package com.qf.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.qf.dao.impl.OrderDetailImpl;
import com.qf.entity.OrderDetail;

public class OrderDetailDaoTest {
	private IOrderDetailDao orderDetail = new OrderDetailImpl();
	@Test
	public void test() {
		int result = orderDetail.addOrderDetail(new OrderDetail(0, 20133, 23, "yyy", 55.5, "xxx", 3, "iii", 555.55, null));
		System.out.println(result);
	}

}
