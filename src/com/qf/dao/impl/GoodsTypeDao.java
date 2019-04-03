package com.qf.dao.impl;

import java.util.List;

import com.qf.dao.IGoodsTypeDao;
import com.qf.entity.GoodsType;
import com.qf.entity.User;
import com.qf.utils.CommonUtils;

public class GoodsTypeDao extends CommonUtils<GoodsType> implements IGoodsTypeDao {

	@Override
	public int addGoodsType(GoodsType goodsType) {
		
		String sql="insert into t_goods_type(gtype_name,gtype_parentid,gtype_pic) values(?,?,?)";
		return CommonUtils.updateCommon(sql, goodsType.getGtype_name(),
				goodsType.getGtype_parentid(),goodsType.getGtype_pic());
	}

	@Override
	public int updateGoodsType(GoodsType goodsType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delGoodsTypeById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GoodsType> getGoodsTypeList() {
		//获取出大类名称的集合
		String sql="select * from t_goods_type where gtype_parentid=0";
		return super.queryCommon(sql, GoodsType.class);
	}

	@Override
	public GoodsType getGoodsTypeById(Integer id) {
		String sql="select * from t_goods_type where id=?";
		List<GoodsType> list = super.queryCommon(sql, GoodsType.class, id);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int getTotalCount() {
		String sql="select count(1) from t_goods_type";
		return CommonUtils.getTotalCount(sql);
	}

	@Override
	public List<GoodsType> getGoodsTypeListPage(Integer startIndex, Integer pageSize) {
		
		String sql="select * from t_goods_type limit ?,?";
		return super.queryCommon(sql, GoodsType.class, startIndex,pageSize);
	}

	@Override  //根据父类id将所属小类集合取出
	public List<GoodsType> getListById(Integer id) {
		String sql="select * from t_goods_type where gtype_parentid=?";
		return super.queryCommon(sql, GoodsType.class, id);
	}

	@Override
	public List<GoodsType> getGoodsTypeAllList() {
		String sql="select * from t_goods_type";
		return super.queryCommon(sql, GoodsType.class);
	}


}
