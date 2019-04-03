package com.qf.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;


public class EncodingFilter implements Filter {


    public EncodingFilter() {
        
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 = (HttpServletResponse) response;
		
		String method = request2.getMethod(); //获取请求方式： GET获取POST
		response2.setContentType("text/html;charset=utf-8");  //GET和POST请求的respone方向
		if("POST".equals(method)){
			request2.setCharacterEncoding("utf-8");  //request的post请求
			chain.doFilter(request, response);
		}else if("GET".equals(method)){
			MyHttpServletRequestWrapper wrapper = new MyHttpServletRequestWrapper(request2);
			chain.doFilter(wrapper, response);  //放行包装请求对象
		}
		
	}
	
	class MyHttpServletRequestWrapper extends HttpServletRequestWrapper{

		public MyHttpServletRequestWrapper(HttpServletRequest request) {
			super(request);
		}
		
		@Override
		public String getParameter(String name) {
			String value = super.getParameter(name);
			if(value != null){//注意：要进行判断，否则，value为null时会出现空指针异常
				try {
					value = new String(value.getBytes("iso-8859-1"), "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			return value;
		}
		
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
