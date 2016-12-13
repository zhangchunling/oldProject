package com.zrgk.permission.service;

import java.util.List;

import com.zrgk.permission.model.Emp_role;
import com.zrgk.permission.model.Employee;
import com.zrgk.permission.model.Menu;
/**
 *
 */
public interface EmployeeServiceInter {

	//1.在进行人员管理时，首先要将所有人员当前的信息全部显示出来
	List<Employee> queryEmployee(Employee employee);
	//2.在人员管理中添加用户信息
	boolean insertEmployee(Employee employee);
	//3.获得当前最大的eid
	int getMaxId();
	//
	void insertEmpRole(Emp_role empRole);//插中间表
	//登录时查询人员
	Employee login(Employee emp);
	//按id查询
	Employee queryEmpById(int eid);
	//修改人员
	void updateEmployee(Employee emp);
	//按id删除人员
	void deleteEmployee(int eid);
	
	//按人员id删除中间表
	void deleteEmp_role(int erid);
	//查询总的数据条数
	int totalSize(Employee emp);
	
	//登录后查每个用户对应的菜单 
	List<Menu> queryLoginMenu(int eid);
	
}
