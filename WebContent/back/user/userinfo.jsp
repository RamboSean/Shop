<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String path = request.getContextPath();
    	String basePath = request.getScheme()+"://" +request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/backstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="">
	$(function(){
		$("#selAll").click(function(){
			//jquery往往2个参数为赋值，则一个参数为赋值；如果1个参数为赋值，则没有参数是取值
			alert($(this).prop("checked"));
			//prop,attr,都是属性的应用，prop更高级
			$(".single").prop("checked",$(this).prop("checked"));
		})

		var array = $(":checkbox[id!='selAll']");
				array.click(function(){
					//当前选中的复选框个数是不是等于全部复选框个数
					var checkedCount = 0;
					array.each(function(){
						if($(this).attr("checked")=="checked"){
							checkedCount++;
						}
					})
					//如果是，就把全选的复选框选上
					if(checkedCount == array.length){
						$("#selAll").attr("checked",true);
					}else{
						//如果不是，就把全选的复选框取消选上
						$("#selAll").attr("checked",false);
					}
					
				})

	//批量删除时获取到id
	$("#batchId").click(function(){
		//获取到多个id后，传到后台去---异步传
		var arr = new Array();
		$(".single:checked").each(function(){
			arr.push($(this).val());
		})
		
        var param = new Object();  //new一个对象
        param.id = arr;
		debugger

		//异步传递  参数2：json格式  也可传对象
		$.get("UserServlet?action=batchDelId",param,function(data){})
			location.reload();  //前端刷新
		})

	})
</script>
</head>

<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">数据表</a></li>
    <li><a href="#">基本内容</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li class="click"><span><img src="images/t01.png" /></span><a href="back/user/adduser.jsp">添加</a></li>
        <li class="click"><span><img src="images/t02.png" /></span>修改</li>
        <li id="batchId"><span><img src="images/t03.png" /></span>批量删除</li>
        <li><span><img src="images/t04.png" /></span>统计</li>
        </ul>
        <ul class="toolbar1">
        <li><span><img src="images/t05.png" /></span>设置</li>
        </ul>
    
    </div>
    
    
      <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" id="selAll"/></th>
        <th>用户编号</th>
        <th>用户名</th>
        <th>密码</th>
        <th>性别</th>
        <th>电话</th>
        <th>邮箱</th>
        <th>是否管理员</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        	<c:forEach items="${page.list}" var="user">
	            <tr>
	         	 <td><input name="" type="checkbox" value="${user.id}" class="single"/></td>
			        <td>${user.id}</td>
			        <td>${user.name}</td>
			        <td>${user.password}</td>
			        <td>${user.sex}</td>
			        <td>${user.phone}</td>
			        <td>${user.email}</td>
			        <td>${user.isAdmin}</td>
			        <td><a href="UserServlet?action=updateInit&id=${user.id}">编辑</a>
			        	<a href="UserServlet?action=delUser&id=${user.id}">删除</a>
			        </td>
		        </tr> 
        	</c:forEach>
	        
        </tbody>
    </table>
    
	<!-- 静态包含 -->
	<%@include file="/common/page.jsp" %>
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
