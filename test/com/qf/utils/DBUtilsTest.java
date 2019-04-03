package com.qf.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class DBUtilsTest {

	@Test  //测试数据库的连接
	public void testGetConnection() {
		//只要不为空，数据库的连接测试成功
		System.out.println(DBUtils.getConnection());
	}

}
