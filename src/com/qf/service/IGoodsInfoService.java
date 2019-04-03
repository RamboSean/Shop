package com.qf.service;

import java.util.List;

import com.qf.entity.GoodsInfo;
import com.qf.entity.Page;

public interface IGoodsInfoService {
	public int addGoodsInfo(GoodsInfo goodsInfo);  //增加
	
	public int updateGoodsInfo(GoodsInfo goodsInfo);  //修改
	
	public int delGoodsInfoById(Integer id);   //删除
	
	public List<GoodsInfo> getGoodsInfoList();   //查询
	
	public GoodsInfo getGoodsInfoById(Integer id);  //查询单个对象

	public Page getPage(String current);  //分页获取page对象
}
