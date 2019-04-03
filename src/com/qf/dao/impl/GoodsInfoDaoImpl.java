package com.qf.dao.impl;

import java.util.List;

import com.qf.dao.IGoodsInfoDao;
import com.qf.entity.GoodsInfo;
import com.qf.utils.CommonUtils;

public class GoodsInfoDaoImpl extends CommonUtils<GoodsInfo> implements IGoodsInfoDao {

	@Override
	public int addGoodsInfo(GoodsInfo goodsInfo) {
		String sql="insert into t_goods_info(goods_name,goods_description,goods_pic,goods_price,goods_stock,goods_price_off,goods_discount,goods_parentid,goods_fatherid) values(?,?,?,?,?,?,?,?,?)";
		return CommonUtils.updateCommon(sql, goodsInfo.getGoods_name(),goodsInfo.getGoods_description(),
				goodsInfo.getGoods_pic(),goodsInfo.getGoods_price(),goodsInfo.getGoods_stock(),
				goodsInfo.getGoods_price_off(),goodsInfo.getGoods_discount(),
				goodsInfo.getGoods_parentid(),goodsInfo.getGoods_fatherid());
	}

	@Override
	public int updateGoodsInfo(GoodsInfo goodsInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delGoodsInfoById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GoodsInfo> getGoodsInfoList() {
		
		String sql="select * from t_goods_info";
		return super.queryCommon(sql, GoodsInfo.class);
	}

	@Override
	public GoodsInfo getGoodsInfoById(Integer id) {
		String sql="select * from t_goods_info where id=?";
		List<GoodsInfo> list = super.queryCommon(sql, GoodsInfo.class, id);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int getTotalCount() {
		String sql="select count(1) from t_goods_info";
		return CommonUtils.getTotalCount(sql);
	}

	@Override
	public List<GoodsInfo> getGoodsInfoListPage(Integer startIndex, Integer pageSize) {
		String sql="select * from t_goods_info limit ?,?";
		return super.queryCommon(sql, GoodsInfo.class, startIndex,pageSize);
	}

}
