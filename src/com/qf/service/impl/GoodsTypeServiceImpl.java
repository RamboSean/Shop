package com.qf.service.impl;

import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import com.qf.dao.IGoodsTypeDao;
import com.qf.dao.impl.GoodsTypeDao;
import com.qf.entity.GoodsType;
import com.qf.entity.Page;
import com.qf.service.IGoodsTypeService;

public class GoodsTypeServiceImpl implements IGoodsTypeService {
	private IGoodsTypeDao goodsTypeDao = new GoodsTypeDao();
	
	@Override
	public int addGoodsType(GoodsType goodsType) {
		return goodsTypeDao.addGoodsType(goodsType);
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
		return goodsTypeDao.getGoodsTypeList();
	}

	@Override
	public GoodsType getGoodsTypeById(Integer id) {
		return goodsTypeDao.getGoodsTypeById(id);
	}

	@Override
	public Page getPage(String current) {
		Integer currentPage = 1;
		Integer pageSize = 4;  //每页显示4条
		if(current != null){
			currentPage = Integer.parseInt(current);
		}
		Page page = new Page(currentPage, pageSize);  //设置当前页和页大小
		
		Integer totalCount = goodsTypeDao.getTotalCount();
		page.setTotalCount(totalCount);   //设置总条数
		
		int pageCount = totalCount/pageSize;
		pageCount = totalCount%pageSize==0?pageCount:pageCount+1;
		page.setPageCount(pageCount);     //设置总页数
		
		Integer startIndex = (currentPage-1)*pageSize;
		List<GoodsType> list = goodsTypeDao.getGoodsTypeListPage(startIndex, pageSize);
		page.setList(list);    //设置数据
		
		page.setUrl("GoodsTypeServlet?action=goodsTypeList");      //设置url
		
		return page;
	}

	@Override
	public List<GoodsType> getListById(Integer id) {
		return goodsTypeDao.getListById(id);
	}

	@Override
	public List<GoodsType> getGoodsTypeAllList() {
		return goodsTypeDao.getGoodsTypeAllList();
	}

}
