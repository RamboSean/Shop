package com.qf.entity;

import java.util.ArrayList;
import java.util.List;

import com.qf.domaiin.GoodsInfoDomain;

public class ShoppingCart {
	//购物车中，使用集合存储商品记录
	private List<GoodsInfoDomain> list = new ArrayList<>();

	public List<GoodsInfoDomain> getList() {
		return list;
	}

	public void setList(List<GoodsInfoDomain> list) {
		this.list = list;
	}
	
	//添加商品
	public void addGoodsInfo(GoodsInfoDomain domain){
		//判断，如果有相同的id，则只需将数量增加即可
		boolean flag = true;
		for(GoodsInfoDomain goodsInfoDomain : list){
			if(goodsInfoDomain.getId().equals(domain.getId())){
				goodsInfoDomain.setCount(goodsInfoDomain.getCount()+domain.getCount());
				flag = false;
				break;
			}
		}
		if(flag){ //如果没有相同id，则存储该记录
			list.add(domain);  //判断
		}

	}
	//删除商品
	public void delGoodsInfo(GoodsInfoDomain domain){
		list.remove(domain);
	}

	public void updateGoodsInfo(Integer id, Integer count) {
		//匹配上id后，将商品的数量变更即可
		for(GoodsInfoDomain domain : list){
			if(domain.getId().equals(id)){
				domain.setCount(count);
				break;
			}
		}
		
	}
	//获取总价格
	public double getPrice(){
		double price = 0.0;
		//遍历购物车中的商品，取出价格合计
		for(GoodsInfoDomain domain : list){
			price += domain.getCount()*domain.getGoods_price();
		}
		return price;
	}
}
