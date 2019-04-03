package com.qf.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.qf.dao.impl.GoodsInfoDaoImpl;
import com.qf.entity.GoodsInfo;

public class GoodsInfoDaoTest {
	private IGoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
	@Test
	public void testAddGoodsInfo() {
		int result = goodsInfoDao.addGoodsInfo(new GoodsInfo(0, "新疆猪头肉", "来自新疆的猪头肉", "xxxx", 33.5, 15, 30.5, 0.8, 3, 27));
		System.out.println(result);
	}

	@Test
	public void testGetGoodsInfoList() {
		List<GoodsInfo> list = goodsInfoDao.getGoodsInfoList();
		for(GoodsInfo goodsInfo : list){
			System.out.println(goodsInfo);
		}
	}

	@Test
	public void testGetGoodsInfoById() {
		GoodsInfo goodsInfo = goodsInfoDao.getGoodsInfoById(43);
		System.out.println(goodsInfo);
	}

	@Test
	public void testGetTotalCount() {
		System.out.println(goodsInfoDao.getTotalCount());
	}

	@Test
	public void testGetGoodsInfoListPage() {
		List<GoodsInfo> list = goodsInfoDao.getGoodsInfoListPage(0, 5);
		for(GoodsInfo goodsInfo : list){
			System.out.println(goodsInfo);
		}
	}

}
