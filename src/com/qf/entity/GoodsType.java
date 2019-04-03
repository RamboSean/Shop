package com.qf.entity;

public class GoodsType {
	private Integer id;
	private String gtype_name;  //商品类别名
	private Integer gtype_parentid;   //父类id
	private String gtype_pic;   //类别图片
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGtype_name() {
		return gtype_name;
	}
	public void setGtype_name(String gtype_name) {
		this.gtype_name = gtype_name;
	}
	public Integer getGtype_parentid() {
		return gtype_parentid;
	}
	public void setGtype_parentid(Integer gtype_parentid) {
		this.gtype_parentid = gtype_parentid;
	}
	public String getGtype_pic() {
		return gtype_pic;
	}
	public void setGtype_pic(String gtype_pic) {
		this.gtype_pic = gtype_pic;
	}
	@Override
	public String toString() {
		return "GoodsType [id=" + id + ", gtype_name=" + gtype_name + ", gtype_parentid=" + gtype_parentid
				+ ", gtype_pic=" + gtype_pic + "]";
	}
	public GoodsType(Integer id, String gtype_name, Integer gtype_parentid, String gtype_pic) {
		super();
		this.id = id;
		this.gtype_name = gtype_name;
		this.gtype_parentid = gtype_parentid;
		this.gtype_pic = gtype_pic;
	}
	public GoodsType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
