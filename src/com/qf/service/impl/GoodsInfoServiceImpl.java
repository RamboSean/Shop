package com.qf.service.impl;

import java.util.List;

import com.qf.dao.IGoodsInfoDao;
import com.qf.dao.impl.GoodsInfoDaoImpl;
import com.qf.entity.GoodsInfo;
import com.qf.entity.Page;
import com.qf.service.IGoodsInfoService;

public class GoodsInfoServiceImpl implements IGoodsInfoService {
	
	private IGoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();

	@Override
	public int addGoodsInfo(GoodsInfo goodsInfo) {
		return goodsInfoDao.addGoodsInfo(goodsInfo);
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
		return goodsInfoDao.getGoodsInfoList();
	}

	@Override
	public GoodsInfo getGoodsInfoById(Integer id) {
		return goodsInfoDao.getGoodsInfoById(id);
	}

	@Override
	public Page getPage(String current) {
		Integer currentPage = 1;
		Integer pageSize    = 5;
		if(current != null){
			currentPage = Integer.parseInt(current);
		}
		Page page = new Page(currentPage, pageSize);  //当前页和页大小的赋值
		
		Integer totalCount = goodsInfoDao.getTotalCount();
		page.setTotalCount(totalCount);  //设置总数
		
		Integer pageCount = totalCount/pageSize;
		pageCount=totalCount%pageSize==0?pageCount:pageCount+1;
		page.setPageCount(pageCount);   //设置页数量
		
		Integer startIndex = (currentPage-1)*pageSize;
		List<GoodsInfo> list = goodsInfoDao.getGoodsInfoListPage(startIndex, pageSize);
		page.setList(list);   //设置数据
		
		page.setUrl("GoodsInfoServlet?action=goodsInfoList");  //设置url
		
		return page;
	}

}
