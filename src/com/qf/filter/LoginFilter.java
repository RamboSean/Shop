package com.qf.filter;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.qf.entity.User;


public class LoginFilter implements Filter {
	private String[] ignorePaths;
	private String[] ignoreActions;

	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest) request;
		
		User user = (User) request2.getSession().getAttribute("backUser");
		String action = request2.getParameter("action");  //获取action参数路径
		String path   = request2.getServletPath();  //获取servlet的访问路径
		if(user != null||isIgnorePath(path)||isIgnoreAction(action)){
			//如果有了登录凭证，则可以放行
			chain.doFilter(request, response);
		}else{
			response.getWriter().write("<script>alert('你没登录，请先登录！');location.href='../backLogin.jsp';</script>");
		}
		
	}

    //判断要忽略的action
	private boolean isIgnoreAction(String action) {
		for(String act:ignoreActions){
			if(act.equals(action)){
				return true;  //找到了要忽略的action
			}
		}
		return false;
	}
	//判断要忽略的path
	private boolean isIgnorePath(String path) {
		for(String pa:ignorePaths){
			if(pa.equals(path)){
				return true;  //找到了要忽略的action
			}
		}
		return false;
	}


	public void init(FilterConfig fConfig) throws ServletException {
		//忽略配置属性中设置的action或path
		Properties properties = new Properties();
		try {
			properties.load(LoginFilter.class.getClassLoader().getResourceAsStream("ignore.properties"));
			ignorePaths = properties.getProperty("path").split(",");
			ignoreActions = properties.getProperty("action").split(",");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
