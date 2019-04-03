package com.qf.dao;

import java.util.List;

import com.qf.entity.GoodsType;

public interface IGoodsTypeDao {
	public int addGoodsType(GoodsType goodsType);  //增加
	
	public int updateGoodsType(GoodsType goodsType);  //修改
	
	public int delGoodsTypeById(Integer id);   //删除
	
	public List<GoodsType> getGoodsTypeList();   //查询
	
	public GoodsType getGoodsTypeById(Integer id);  //查询单个对象
	
	public int getTotalCount();   //获取总条数

	public List<GoodsType> getGoodsTypeListPage(Integer startIndex, Integer pageSize);

	public List<GoodsType> getListById(Integer id);

	public List<GoodsType> getGoodsTypeAllList();
}
