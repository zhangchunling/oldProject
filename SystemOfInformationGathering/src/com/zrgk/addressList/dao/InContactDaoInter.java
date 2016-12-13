package com.zrgk.addressList.dao;

import java.util.List;

import com.zrgk.permission.model.Employee;

public interface InContactDaoInter {
	//查询
	public  List<Employee> queryInContact(Employee employee);
		
}
