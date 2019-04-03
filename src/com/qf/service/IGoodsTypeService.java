package com.qf.service;

import java.util.List;

import com.qf.entity.GoodsType;
import com.qf.entity.Page;

public interface IGoodsTypeService {
	public int addGoodsType(GoodsType goodsType);  //增加
	
	public int updateGoodsType(GoodsType goodsType);  //修改
	
	public int delGoodsTypeById(Integer id);   //删除
	
	public List<GoodsType> getGoodsTypeList();   //查询
	
	public GoodsType getGoodsTypeById(Integer id);  //查询单个对象

	public Page getPage(String current);

	public List<GoodsType> getListById(Integer id);

	public List<GoodsType> getGoodsTypeAllList();
}
