package com.qf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.qf.dao.IOrderDetailDao;
import com.qf.entity.OrderDetail;
import com.qf.utils.CommonUtils;
import com.qf.utils.DBUtils;

public class OrderDetailImpl extends CommonUtils<OrderDetail> implements IOrderDetailDao {

	@Override
	public int addOrderDetail(OrderDetail detail) {
		String sql="insert into t_order_detail(o_orderid,goodsid,goodsname,goodsprice,goods_description,goodsnum,goodspic,goods_total_price,goods_date) values(?,?,?,?,?,?,?,?,now())";
		return CommonUtils.updateCommon(sql, detail.getO_orderid(),detail.getGoodsid(),
				detail.getGoodsname(),detail.getGoodsprice(),detail.getGoods_description(),
				detail.getGoodsnum(),detail.getGoodspic(),detail.getGoods_total_price());
	}

	@Override  //批处理
	public void batchaddOrderDetail(List<OrderDetail> list) {
		Connection conn = null;
		PreparedStatement prst = null;
		String sql="insert into t_order_detail(o_orderid,goodsid,goodsname,goodsprice) values(?,?,?,?)";
		try {
			conn = DBUtils.getConnection();
			prst = conn.prepareStatement(sql);
			for(OrderDetail detail : list){
				prst.setInt(1, detail.getO_orderid());
				prst.setInt(2, detail.getGoodsid());
				prst.setString(3, detail.getGoodsname());
				prst.setDouble(4, detail.getGoodsprice());
				prst.addBatch();  //将每条记录放入批处理中
			}
			prst.executeBatch();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
