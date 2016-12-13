package com.zrgk.self.service;

import java.util.List;

import com.zrgk.permission.model.Employee;
import com.zrgk.self.dao.EmployeeDaoInter;

//【继承了HibernateDaoSupport后，session可直接得到了】
public class EmployeeServiceImpl  implements EmployeeServiceInter{

	private EmployeeDaoInter selfDao;

	//按Id查询人员
	@Override
	public Employee queryEmpById(int eid) {
		Employee employee = selfDao.queryEmpById(eid);
		return employee;
	}
	
	@Override
	public void updateEmployee(Employee emp) {
		selfDao.updateEmployee(emp);
		
	}
	
	//统计总的数据条数
	@Override
	public int totalSize(Employee emp) {
		int nums=selfDao.totalSize(emp);
		return nums;
	}
	

	public EmployeeDaoInter getSelfDao() {
		return selfDao;
	}

	public void setSelfDao(EmployeeDaoInter selfDao) {
		this.selfDao = selfDao;
	}

	@Override
	public List<Employee> queryEmployee(Employee employee) {
		List<Employee> list=selfDao.queryEmployee(employee);
		return list;
	}

	

	



}
