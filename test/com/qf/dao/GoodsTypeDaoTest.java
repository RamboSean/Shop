package com.qf.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.qf.dao.IGoodsTypeDao;
import com.qf.dao.impl.GoodsTypeDao;
import com.qf.entity.GoodsType;

public class GoodsTypeDaoTest {

	private IGoodsTypeDao goodsTypeDao = new GoodsTypeDao();
	@Test
	public void testAddGoodsType() {
		int result = goodsTypeDao.addGoodsType(new GoodsType(0, "猪头肉", 3, "yyy"));
		System.out.println(result);
	}

	@Test
	public void testGetGoodsTypeList() {
		List<GoodsType> list = goodsTypeDao.getGoodsTypeList();
		for(GoodsType goodsType:list){
			System.out.println(goodsType);
		}
	}

	@Test
	public void testGetGoodsTypeById() {
		GoodsType goodsType = goodsTypeDao.getGoodsTypeById(27);
		System.out.println(goodsType);
	}

	@Test
	public void testGetTotalCount() {
		int result = goodsTypeDao.getTotalCount();
		System.out.println(result);
	}

	@Test
	public void testGetGoodsTypeListPage() {
		List<GoodsType> list = goodsTypeDao.getGoodsTypeListPage(0, 4);
		for(GoodsType goodsType:list){
			System.out.println(goodsType);
		}
	}
	
	@Test  //根据大类id获取所属小类集合
	public void testGetListById() {
		List<GoodsType> list = goodsTypeDao.getListById(3);
		for(GoodsType goodsType:list){
			System.out.println(goodsType);
		}
	}

}
