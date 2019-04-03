package com.qf.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.entity.GoodsType;
import com.qf.entity.Page;
import com.qf.service.IGoodsTypeService;
import com.qf.service.impl.GoodsTypeServiceImpl;


public class GoodsTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IGoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if("goodsTypeList".equals(action)){
			//------分页功能------
		    String current=request.getParameter("currentPage");
		    Page page = goodsTypeService.getPage(current);
		    request.setAttribute("page", page);
		    request.getRequestDispatcher("back/goodstype/goodstype.jsp").forward(request, response);
		}else if("showGoodsType".equals(action)){//显示商品类别名称
			List<GoodsType> list = goodsTypeService.getGoodsTypeList();
			request.setAttribute("list", list);
			request.getRequestDispatcher("back/goodstype/goodsadd.jsp").forward(request, response);
		}else if("addGoodsType".equals(action)){
			String gtype_name = request.getParameter("name");
			Integer gtype_parentid = Integer.parseInt(request.getParameter("parentId"));
			int result =goodsTypeService.addGoodsType(new GoodsType(0, gtype_name, gtype_parentid, "xxxx"));
			//---可以做判断----
			response.sendRedirect("GoodsTypeServlet?action=goodsTypeList");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
