package com.qf.dao.impl;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import com.qf.dao.IUserDao;
import com.qf.entity.User;
import com.qf.utils.CommonUtils;
import com.qf.utils.DBUtils;

public class UserDaoImpl extends CommonUtils<User> implements IUserDao {

	@Override
	public int addUser(User user) {
		String sql = "insert into t_user(name,password,sex,phone,email,is_admin) values(?,?,?,?,?,?)";
		int result = CommonUtils.updateCommon(sql, user.getName(),user.getPassword(),user.getSex()
				,user.getPhone(),user.getEmail(),user.getIsAdmin());
		return result;
	}

	@Override
	public int updateUser(User user) {
		String sql="update t_user set name=?,password=?,sex=?,phone=?,email=?,is_admin=? where id=?";
		int result = CommonUtils.updateCommon(sql, user.getName(),user.getPassword(),user.getSex()
				,user.getPhone(),user.getEmail(),user.getIsAdmin(),user.getId());
		return result;
	}

	@Override
	public int delUserById(Integer id) {
		String sql="delete from t_user where id=?";
		return CommonUtils.updateCommon(sql, id);
	}

	@Override
	public List<User> getUserList() {
		String sql="SELECT id, NAME, PASSWORD, CASE WHEN sex = 1 THEN '男' WHEN sex = 0 THEN '女' END AS sex, email, phone, CASE WHEN is_admin = 1 THEN '是' WHEN is_admin = 0 THEN '否' END AS is_admin FROM t_user";
		return super.queryCommon(sql, User.class);
	}

	@Override
	public User getUserById(Integer id) {
		String sql="select * from t_user where id = ?";
		List<User> list = super.queryCommon(sql, User.class,id);
		if(list.size()>0){  //如果取到数据，则返回该对象
			return list.get(0);
		}
		return null;  //取不到数据，则返回null
	}

	@Override
	public int getTotalCount() {
		String sql="select count(1) from t_user";
		return CommonUtils.getTotalCount(sql);
	}

	@Override
	public List<User> getUserListPage(Integer startIndex, Integer pageSize) {
		String sql="SELECT id, NAME, PASSWORD, CASE WHEN sex = 1 THEN '男' WHEN sex = 0 THEN '女' END AS sex, email, phone, CASE WHEN is_admin = 1 THEN '是' WHEN is_admin = 0 THEN '否' END AS is_admin FROM t_user limit ?,?";
		return super.queryCommon(sql, User.class,startIndex,pageSize);
	}

	@Override  //批量删除
	public int batchDelIds(String[] ids) {
		StringBuilder builder = new StringBuilder("delete from t_user where id in (");
		for(int i=0;i<ids.length;i++){
			if(i==ids.length-1){  //最后一个则不用+“，”；要加“）”
				builder.append(ids[i]+")");
			}else{
				builder.append(ids[i]+",");
			}
		}
		String sql = builder.toString();
		return CommonUtils.updateCommon(sql);
	}

	@Override   //验证用户名和密码是否正确
	public User getUserByBackLogin(String name, String password) {
	
		String sql="select * from t_user where name=? and password=?";
		List<User> list = super.queryCommon(sql, User.class, name,password);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override  //前端用户的登录判断
	public User getUserByFrontLogin(String name, String password) {
		String sql="select * from t_user where name=? and password=?";
		List<User> list = super.queryCommon(sql, User.class, name,password);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
