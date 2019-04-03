package com.qf.dao;

import java.util.List;

import com.qf.entity.GoodsInfo;

public interface IGoodsInfoDao {
	public int addGoodsInfo(GoodsInfo goodsInfo);  //增加
	
	public int updateGoodsInfo(GoodsInfo goodsInfo);  //修改
	
	public int delGoodsInfoById(Integer id);   //删除
	
	public List<GoodsInfo> getGoodsInfoList();   //查询
	
	public GoodsInfo getGoodsInfoById(Integer id);  //查询单个对象
	
	public int getTotalCount();   //获取总条数

	public List<GoodsInfo> getGoodsInfoListPage(Integer startIndex, Integer pageSize);
}
