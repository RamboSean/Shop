package com.qf.dao.impl;

import java.util.List;

import com.qf.dao.IAddressDao;
import com.qf.entity.Address;
import com.qf.utils.CommonUtils;

public class AddressDaoImpl extends CommonUtils<Address> implements IAddressDao {

	@Override
	public List<Address> getAddressListByUserId(Integer userId) {
		
		String sql="select * from t_address where userid=?";
		return super.queryCommon(sql, Address.class, userId);
	}

}
