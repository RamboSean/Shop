package com.qf.service;

import java.util.List;

import com.qf.entity.Address;

public interface IAddressService {
	public List<Address> getAddressListByUserId(Integer userId);
}
