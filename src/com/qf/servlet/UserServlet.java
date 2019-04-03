package com.qf.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.entity.Page;
import com.qf.entity.User;
import com.qf.service.IUserService;
import com.qf.service.impl.UserServiceImpl;


public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	//用户的业务层助手
	IUserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if("userList".equals(action)){
			
			/*List<User> list = userService.getUserList();  //调查询方法
			System.out.println(list);
			//获取到集合对象后，将数据库展示到前端
			request.setAttribute("list", list);  //request+转发
			*/
			
			//-----使用分页显示功能-----
			String current = request.getParameter("currentPage");  //获取前端传入的当前页
			Page page = userService.getPage(current);  //在业务层给page对象赋值
			request.setAttribute("page", page);
			
			request.getRequestDispatcher("back/user/userinfo.jsp").forward(request, response);
		}else if("delUser".equals(action)){ //删除功能
			Integer id = Integer.parseInt(request.getParameter("id"));
			int result = userService.delUserById(id);  //删除id对应的记录
			responseToClient(result, response);   //提示的展示
		}else if("updateInit".equals(action)){ //更新初始化
			Integer id = Integer.parseInt(request.getParameter("id"));
			User user = userService.getUserById(id); //传入id，得到对象
			request.setAttribute("user", user);
			request.getRequestDispatcher("back/user/updateuser.jsp").forward(request, response);
		}else if("updateUser".equals(action)){  //更新用户
			User user = getUserFromParam(request);  //传入参数，得到user对象
			int result = userService.updateUser(user);
			responseToClient(result,response);  //提示的展示
			
		}else if("addUser".equals(action)){  //添加用户对象
			User user = getUserFromParam(request);  //传入参数，得到user对象
			int result = userService.addUser(user);
			responseToClient(result, response);  //提示的展示
		}else if("batchDelId".equals(action)){  //批量删除
			String[] ids = request.getParameterValues("id[]");
			int result = userService.batchDelIds(ids);
			responseToClient(result, response);
		}else if("backLogin".equals(action)){
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			
			//-----------先进行验证码的校验-----------
			String code = request.getParameter("code");
			
			String imgCode = (String) request.getSession().getAttribute("imgCode");
			if(!code.equals(imgCode)){
				response.getWriter().write("<script>alert('验证码出错啦~');location.href='backLogin.jsp';</script>");
				return;
			}
			
			User user = userService.getUserByBackLogin(name,password);
			if(user != null){
				if("1".equals(user.getIsAdmin())){
					request.getSession().setAttribute("backUser", user);  //设置登录凭证
					response.sendRedirect("back/main.jsp");  //登录成功则进入后台首页
				}else{
					response.getWriter().write("<script>alert('你不是管理员，请联系管理员~');location.href='backLogin.jsp';</script>");
				}
			}else{
				response.getWriter().write("<script>alert('用户名或密码出错，请重新输入！');location.href='backLogin.jsp';</script>");
			}
		}else if("frontLogin".equals(action)){  //前端登录
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			User user = userService.getUserByFrontLogin(name,password);
			if(user != null){
				//登录成功，存储前端用户凭证
				request.getSession().setAttribute("frontUser", user);
				response.sendRedirect("home.jsp");
			}
		}else if("logout".equals(action)){
			request.getSession().invalidate();   //清除session对象
			response.sendRedirect("home.jsp");
		}
	}
	
	private User getUserFromParam(HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String isAdmin = request.getParameter("isAdmin");
		Integer nid = 0;
		if(id != null){ //如果是updateUser调用的，则会取到参数的id值，如果是添加，则直接跳过
			nid = Integer.parseInt(id);
		}
		return new User(nid, name, password, sex, phone, email, isAdmin);
	}



	private void responseToClient(int result, HttpServletResponse response) throws IOException {
		if(result<=0){
			response.getWriter().write("<script>alert('操作失败');location.href='UserServlet?action=userList';</script>");
		}else{
			response.sendRedirect("UserServlet?action=userList");  //修改后展示数据
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
