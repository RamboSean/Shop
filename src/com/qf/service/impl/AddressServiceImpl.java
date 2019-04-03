package com.qf.service.impl;

import java.util.List;

import com.qf.dao.IAddressDao;
import com.qf.dao.impl.AddressDaoImpl;
import com.qf.entity.Address;
import com.qf.service.IAddressService;

public class AddressServiceImpl implements IAddressService {

	private IAddressDao addressDao = new AddressDaoImpl();
	@Override
	public List<Address> getAddressListByUserId(Integer userId) {
		return addressDao.getAddressListByUserId(userId);
	}

}
