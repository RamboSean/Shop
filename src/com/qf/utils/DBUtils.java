package com.qf.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
	private static String driverName;
	private static String url;
	private static String username;
	private static String password;
	static{
		try {
			//读取配置文件的信息
			Properties properties = new Properties();
			//从资源路径中去获取配置属性
			InputStream is = DBUtils.class.getClassLoader().getResourceAsStream("dbconfig.properties");
			try {
				properties.load(is);
				driverName = properties.getProperty("driverName");
				url = properties.getProperty("url");
				username = properties.getProperty("username");
				password = properties.getProperty("password");
			} catch (IOException e) {
				e.printStackTrace();
			}
			//1. 加载驱动
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		//2. 获取连接对象
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void closeAll(AutoCloseable...cs){
		if(cs != null){  //如果只传了一个null过来，则不用进行遍历
			for(AutoCloseable c: cs){
				if(c != null){  //如果多个参数中有一个为null，则不用关闭
					try {
						c.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	
}
