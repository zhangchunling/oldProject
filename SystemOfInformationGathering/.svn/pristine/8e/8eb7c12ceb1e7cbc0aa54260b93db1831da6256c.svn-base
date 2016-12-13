package com.zrgk.permission.service.impl;

import java.util.List;


import com.zrgk.permission.dao.EmployeeDaoInter;
import com.zrgk.permission.model.Emp_role;
import com.zrgk.permission.model.Employee;
import com.zrgk.permission.model.Menu;
import com.zrgk.permission.service.EmployeeServiceInter;
import com.zrgk.utils.MD5;

public class EmployeeServiceImpl implements EmployeeServiceInter{
	private EmployeeDaoInter employeeDao;//set注入：声明一个存dao层接口的属性，然后生成get、set方法

	@Override//查询所有人员
	public List<Employee> queryEmployee(Employee employee) {
		List<Employee> list=employeeDao.queryEmployee(employee);
		return list;
	}

	@Override//插入人员
	public boolean insertEmployee(Employee employee) {
		//加密
		String sec=MD5.createPassword(employee.getPassword());
		employee.setPassword(sec);
		//插入
		employeeDao.insertEmployee(employee);
		return false;
	}
	
	@Override//得到当前最大eid，用于插入当前人员时，插入中间表用
	public int getMaxId() {
		int num=employeeDao.getMaxId();
		return num;
	}
	@Override//插入人员-角色中间表
	public void insertEmpRole(Emp_role empRole) {
		employeeDao.insertEmpRole(empRole);
		
	}
	//登录时查询人员
	@Override
	public Employee login(Employee emp) {
		//加密：数据库的密码是按规则加密的了，所以去匹配数据库时也得加密
		String sec=MD5.createPassword(emp.getPassword());
		emp.setPassword(sec);
		//查回人员
		Employee employee = employeeDao.login(emp);		
		return employee;
	}
	//按id查询
	@Override
	public Employee queryEmpById(int eid) {
		Employee emp=employeeDao.queryEmpById(eid);
		return emp;
	}

	//更新
	@Override
	public void updateEmployee(Employee emp) {
		employeeDao.updateEmployee(emp);
		
	}

	//按id删除
	@Override
	public void deleteEmployee(int eid) {
		employeeDao.deleteEmployee(eid);		
	}

	//按人员Id删除中间表
	@Override
	public void deleteEmp_role(int erid) {
		employeeDao.deleteEmp_role(erid);		
	}
	
	//统计总的数据条数
	@Override
	public int totalSize(Employee emp) {
		int nums=employeeDao.totalSize(emp);
		return nums;
	}
	
	//登录后查询用户对应的菜单
	@Override
	public List<Menu> queryLoginMenu(int eid) {
		return employeeDao.queryLoginMenu(eid);
	}
	
	//set注入生成的get、set方法

	public EmployeeDaoInter getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDaoInter employeeDao) {
		this.employeeDao = employeeDao;
	}

	

	

	

	
	
	
	
	

	
}
