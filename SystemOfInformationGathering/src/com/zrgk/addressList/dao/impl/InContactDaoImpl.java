package com.zrgk.addressList.dao.impl;

import java.awt.image.RescaleOp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zrgk.addressList.dao.InContactDaoInter;
import com.zrgk.permission.model.Employee;

public class InContactDaoImpl extends HibernateDaoSupport implements InContactDaoInter{
	private Employee employee;
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public List<Employee> queryInContact(Employee employee) {
		Criteria criteria=this.getSession().createCriteria(Employee.class);
		if(employee!=null&&employee.getRealName()!=null&&!"".equals(employee.getRealName())){
			criteria.add(Restrictions.like("realName", "%"+employee.getRealName()+"%"));
		}else
			if(employee!=null&&employee.getTel()!=null&&!"".equals(employee.getTel())){
				criteria.add(Restrictions.like("tel", "%"+employee.getTel()+"%"));
			}
		List<Employee> list = criteria.list();
			return list;
	}
	

}
