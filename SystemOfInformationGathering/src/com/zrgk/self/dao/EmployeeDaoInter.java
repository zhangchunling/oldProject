package com.zrgk.self.dao;

import java.util.List;

import com.zrgk.permission.model.Emp_role;
import com.zrgk.permission.model.Employee;
import com.zrgk.permission.model.Menu;

public interface EmployeeDaoInter {

	//按id查询
	Employee queryEmpById(int eid);
	
	//修改人员
	void updateEmployee(Employee emp);
	//1.在进行人员管理时，首先要将所有人员当前的信息全部显示出来,放到一个集合里
		List<Employee> queryEmployee(Employee employee);
		
		//查询总的数据条数
		int totalSize(Employee emp);
}
