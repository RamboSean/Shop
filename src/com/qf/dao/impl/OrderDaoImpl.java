package com.qf.dao.impl;

import com.qf.dao.IOrderDao;
import com.qf.entity.Order;
import com.qf.utils.CommonUtils;

public class OrderDaoImpl extends CommonUtils<Order> implements IOrderDao {

	@Override
	public int addOrder(Order order) {
		String sql="insert into t_order(o_sendtype,o_paytype,o_paycount,userid,o_shperson,o_shphone,o_shaddress,o_orderdate) values(?,?,?,?,?,?,?,now())";
		return CommonUtils.updateCommon(sql, order.getO_sendtype(),order.getO_paytype(),order.getO_paycount()
				,order.getUserid(),order.getO_shperson(),order.getO_shphone(),order.getO_shaddress());
	}

	@Override
	public int addAndReturnPrimaryKey(Order order) {
		String sql="insert into t_order(o_sendtype,o_paytype,o_paycount,userid,o_shperson,o_shphone,o_shaddress,o_orderdate) values(?,?,?,?,?,?,?,now())";
		return CommonUtils.addAndReturnPrimaryKey(sql, order.getO_sendtype(),order.getO_paytype(),order.getO_paycount()
				,order.getUserid(),order.getO_shperson(),order.getO_shphone(),order.getO_shaddress());
	}

}
