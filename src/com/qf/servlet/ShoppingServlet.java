package com.qf.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.qf.domaiin.GoodsInfoDomain;
import com.qf.entity.Address;
import com.qf.entity.GoodsInfo;
import com.qf.entity.GoodsType;
import com.qf.entity.Order;
import com.qf.entity.OrderDetail;
import com.qf.entity.ShoppingCart;
import com.qf.entity.User;
import com.qf.service.IAddressService;
import com.qf.service.IGoodsInfoService;
import com.qf.service.IGoodsTypeService;
import com.qf.service.IOrderDetailService;
import com.qf.service.IOrderService;
import com.qf.service.impl.AddressServiceImpl;
import com.qf.service.impl.GoodsInfoServiceImpl;
import com.qf.service.impl.GoodsTypeServiceImpl;
import com.qf.service.impl.OrderDetailServiceImpl;
import com.qf.service.impl.OrderServiceImpl;


public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//商品类别和商品信息的助手
	private IGoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();
	private IGoodsInfoService goodsInfoService = new GoodsInfoServiceImpl();
	
	//获取地址的业务层对象
	private IAddressService addressService = new AddressServiceImpl();
	
	//订单的业务层对象
	private IOrderService orderService = new OrderServiceImpl();
	
	//订单明细的业务层对象
	private IOrderDetailService orderDetailService = new OrderDetailServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if("findAll".equals(action)){
			//将商品类别和商品信息都带出来，然后展示到前端首页（index.jsp）
			//获取所有商品类别的集合数据
			List<GoodsType> gtList = goodsTypeService.getGoodsTypeAllList();
			//获取所有商品的集合数据
			List<GoodsInfo> giList = goodsInfoService.getGoodsInfoList();
			request.setAttribute("gtList", gtList);
			request.setAttribute("giList", giList);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else if("showById".equals(action)){//展示单个商品信息
			Integer id = Integer.parseInt(request.getParameter("id"));
			GoodsInfo goodsInfo = goodsInfoService.getGoodsInfoById(id);  //根据id获取商品对象
			request.setAttribute("goodsInfo", goodsInfo);
			request.getRequestDispatcher("introduction.jsp").forward(request, response);
		}else if("toShoppingCart".equals(action)){
			Integer id = Integer.parseInt(request.getParameter("id"));
			Integer count = Integer.parseInt(request.getParameter("count"));
			//根据得到的id和count将商品记录加入购物车
			GoodsInfo goodsInfo = goodsInfoService.getGoodsInfoById(id);
			
			//实例化domain，然后将GoodsInfo数据注入进去
			GoodsInfoDomain domain = new GoodsInfoDomain();
			try {
				BeanUtils.copyProperties(domain, goodsInfo);
				domain.setCount(count);  //写入数量
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
			
			//购物车放入到session中，可以确保每个用户就是独立的购物车
			ShoppingCart cart = getShoppingCart(request.getSession());
			cart.addGoodsInfo(domain);  //添加到购物车
		}else if("update".equals(action)){
			Integer id = Integer.parseInt(request.getParameter("id"));
			Integer count = Integer.parseInt(request.getParameter("count"));
			ShoppingCart cart = getShoppingCart(request.getSession());
			cart.updateGoodsInfo(id,count);  //更新商品的数量
		}else if("pay".equals(action)){  //结算
			User user = (User) request.getSession().getAttribute("frontUser");
			if(user != null){  //已经记录了登录凭证
				//根据user对象的id将对应地址表中的记录获取出来---创建Address的实体类
				List<Address> list = addressService.getAddressListByUserId(user.getId());
				request.setAttribute("list", list);
				request.getRequestDispatcher("pay.jsp").forward(request, response);;
			}else{
				response.sendRedirect("login.jsp");
			}
		}else if("submitOrder".equals(action)){
			String shouhuoren = request.getParameter("shouhuoren");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String express = request.getParameter("express");
			String bank = request.getParameter("bank");
			
			//获取到发货信息和物流方式后，形成一个订单
			//需要考虑的参数：  userid，总金额
			//userid: 判断前端用户凭证，如果有，则获取user对象取出id
			//总金额：需要从购物车总获取
			ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("shoppingCart");
			User user = (User) request.getSession().getAttribute("frontUser");
			if(user != null){  //已经记录了登录凭证
				//常规方式： 返回影响条数
				//int result = orderService.addOrder(new Order(0, express, bank, cart.getPrice(), null, user.getId(), shouhuoren, phone, address));
				//System.out.println(result);
				
				//主键回填：返回订单id
				int orderId = orderService.addAndReturnPrimaryKey(new Order(0, express, bank, cart.getPrice(), null, user.getId(), shouhuoren, phone, address));
				
				//-------订单明细的处理---------
				//注意： 一个订单有多条商品明细记录
				//问题： 一个订单多条明细，如何进行关联
				//方式1： 订单id不用自增长，手动传入；再将该订单id传到明细表中
				//方式2：主键回填--（重要）添加订单后，返回出订单id
				
				//从购物车中获取出商品明细
/*				for(GoodsInfoDomain domain:cart.getList()){
					//注意：将添加操作放入循环，交互次数变多，如果出现异常，则循环报错
					OrderDetail detail = new OrderDetail(0, orderId, domain.getId(), domain.getGoods_name(), domain.getGoods_price(), domain.getGoods_description(), domain.getCount(), domain.getGoods_pic(),domain.getGoods_price()*domain.getCount() , null);
					orderDetailService.addOrderDetail(detail);
				}*/
				
				//---------批处理---------
				List<OrderDetail> list = new ArrayList<>();
				for(GoodsInfoDomain domain:cart.getList()){
					OrderDetail detail = new OrderDetail(0, orderId, domain.getId(), domain.getGoods_name(),
							domain.getGoods_price(), domain.getGoods_description(), domain.getCount(), domain.getGoods_pic(),
							domain.getGoods_price()*domain.getCount() , null);
					list.add(detail);  //集合添加多条商品明细
				}
				orderDetailService.batchaddOrderDetail(list);  //批处理订单明细集合
				
				request.getRequestDispatcher("success.jsp").forward(request, response);
			}else{
				response.sendRedirect("login.jsp");
			}
			
		}
		
	}

	private ShoppingCart getShoppingCart(HttpSession session) {
		ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
		if(cart == null){
			cart = new ShoppingCart();
			session.setAttribute("shoppingCart", cart);  //将购物车存储到session
		}
		return cart;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
