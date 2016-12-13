package com.zrgk.addressList.service.impl;

import java.util.List;

import com.zrgk.addressList.dao.InContactDaoInter;
import com.zrgk.addressList.service.InContactServiceInter;
import com.zrgk.permission.model.Employee;

public class InCintactServiceImpl implements InContactServiceInter{
	private InContactDaoInter inContactDao;
	
	
	public InContactDaoInter getInContactDao() {
		return inContactDao;
	}


	public void setInContactDao(InContactDaoInter inContactDao) {
		this.inContactDao = inContactDao;
	}

	//查询
	@Override
	public List<Employee> queryInContact(Employee employee) {
	
		return inContactDao.queryInContact(employee);
	}

}
