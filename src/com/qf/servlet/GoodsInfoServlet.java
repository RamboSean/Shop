package com.qf.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.qf.entity.GoodsInfo;
import com.qf.entity.GoodsType;
import com.qf.entity.Page;
import com.qf.service.IGoodsInfoService;
import com.qf.service.IGoodsTypeService;
import com.qf.service.impl.GoodsInfoServiceImpl;
import com.qf.service.impl.GoodsTypeServiceImpl;

public class GoodsInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IGoodsInfoService goodsInfoService = new GoodsInfoServiceImpl();
	private IGoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if("goodsInfoList".equals(action)){
			//-----直接做分页展示
			String current = request.getParameter("currentPage");
			Page page = goodsInfoService.getPage(current);
			request.setAttribute("page", page);
			request.getRequestDispatcher("back/goods/goodsList.jsp").forward(request, response);
			
		}else if("showGoods".equals(action)){
			//将大类名称和id获取出来
			List<GoodsType> gtList = goodsTypeService.getGoodsTypeList();
			request.setAttribute("gtList", gtList);
			request.getRequestDispatcher("back/goods/goodsadd.jsp").forward(request, response);
			
		}else if("getSmall".equals(action)){
			Integer id = Integer.parseInt(request.getParameter("id"));
			List<GoodsType> list = goodsTypeService.getListById(id);  //根据父类id，将所属小类都获取出来
			//根据集合产生json数据
			String json = JSON.toJSONString(list);
			response.getWriter().write(json);
			
		}else if("addGoodsInfo".equals(action)){  //文件上传的操作
			//1.实例化DiskFileItemFactory的工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2.ServletFileUpload对象需要从工厂中去获取
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			GoodsInfo goodsInfo = new GoodsInfo();
			Map<String, Object> map = new HashMap<>();
			
			try {
				//3. 通过文件上传对象解析request中的数据
				//将数据放入集合
				List<FileItem> list = fileUpload.parseRequest(request);
				//遍历集合取出对象
				for(FileItem item : list){
					System.out.println(item);
					if(item.isFormField()){ //是文本表单内容---普通内容
						//map集合的数据注入到对象中即可
						map.put(item.getFieldName(), item.getString("utf-8"));
					}else{    //取图片内容
						//获取到tomcat中部署的路径--部署路径重新加载时会删除上传图片（虚拟目录）
						//变更后，存到真实指定路径
						String path ="E:\\eclipse\\Objects\\Day46_Shop_4\\WebContent\\images";
						//String path = getServletContext().getRealPath("images");
						//判断path路径是否存在，如果不存在则创建
						File file = new File(path);
						if(!file.exists()){
							file.mkdirs();   //创建目录
						}
						//uuid+文件名--->处理重名问题
						String fileName = UUID.randomUUID()+"_"+item.getName();
						File allFile = new File(file, fileName);
						//将源图片资源流拷贝到目标资源路径中---存储时，存的是完整路径
						IOUtils.copy(item.getInputStream(), new FileOutputStream(allFile));
						
						goodsInfo.setGoods_pic(fileName);  //存储图片名
					}
				}
				
				//将map集合的数据注入到GoodsInfo中 对象中的属性名要与map的key要对应
				BeanUtils.populate(goodsInfo, map);
				int result = goodsInfoService.addGoodsInfo(goodsInfo);
				response.sendRedirect("GoodsInfoServlet?action=goodsInfoList");
				
			} catch (FileUploadException | IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
