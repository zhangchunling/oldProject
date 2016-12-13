package com.zrgk.permission.dao;

import java.util.List;

import com.zrgk.permission.model.Emp_role;
import com.zrgk.permission.model.Employee;
import com.zrgk.permission.model.Menu;

public interface EmployeeDaoInter {

	//1.在进行人员管理时，首先要将所有人员当前的信息全部显示出来,放到一个集合里
	List<Employee> queryEmployee(Employee employee);
	//2.在人员管理中添加用户信息时，当点击保存的时候，插入一条新的用户信息
	boolean insertEmployee(Employee employee);
	//3.获得当前最大的eid
	int getMaxId();
	//4.插入人员——角色中间表
	void insertEmpRole(Emp_role empRole);
	//登录查所有
	Employee login(Employee employee);
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
