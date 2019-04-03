package com.qf.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.qf.dao.impl.AddressDaoImpl;
import com.qf.entity.Address;

public class AddressDaoTest {
	private IAddressDao addressDao = new AddressDaoImpl();
	@Test
	public void testGetAddressListByUserId() {
		List<Address> list = addressDao.getAddressListByUserId(37);
		for(Address address : list){
			System.out.println(address);
		}
	}

}
