package com.zrgk.self.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zrgk.permission.model.Emp_role;
import com.zrgk.permission.model.Employee;
import com.zrgk.permission.model.Menu;

//【继承了HibernateDaoSupport后，session可直接得到了】
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDaoInter{

	

	//按Id查询人员
	@Override
	public Employee queryEmpById(int eid) {
		Session session=this.getSession();
		Criteria cri=session.createCriteria(Employee.class);
		Employee emp=new Employee();
		cri.add(Example.create(emp));
		List list=cri.list();
		Employee employee=null;
		for(int i=0;i<list.size();i++){
			emp=(Employee) list.get(i);
			if(emp.getEid()==eid){
				employee=emp;
				String sql="select r.roleName from role r ,employee e, emp_role er where e.eid=er.eid and er.rid=r.rid and e.eid="+eid;
				Query query=session.createSQLQuery(sql);
				List list2=query.list();
				String str="";
				Iterator it=list2.iterator();
				while(it.hasNext()){
					String name=(String) it.next();
					str=str+name+",";
				}
				
				if(str!=null&&!"".equals(str.trim())){
					String s=str.substring(0, str.length()-1);
					employee.setStrRole(s);
				}
				
				
			}
		}
		session.close();
		return employee;
	}


	@Override
	public void updateEmployee(Employee emp) {
		this.getHibernateTemplate().update(emp);
	
	}
	
	// 查询人员
	public List<Employee> queryEmployee(Employee employee) {
		Session session=this.getSession();//得到session
		Criteria cri=session.createCriteria(Employee.class);//创建条件查询
		
		//模糊查询
		if(employee!=null&&employee.getEmpName()!=null&&!"".equals(employee.getEmpName())){
			cri.add(Restrictions.ilike("empName", "%"+employee.getEmpName()+"%"));
		}else if (employee!=null&&employee.getTel()!=null&&!"".equals(employee.getTel())) {
			cri.add(Restrictions.ilike("tel", "%"+employee.getTel()+"%"));
		}	
		//按Id倒序排
		cri.addOrder(Order.desc("eid"));
		//分页，从第几条数据开始取
		if(employee!=null){
			cri.setFirstResult((employee.getCurrentPage()-1)*5);
			cri.setMaxResults(5);
		}		
		
		//执行
		List<Employee> list=cri.list();	
		
		session.close();
		return list;		
	}

	
	
	//查询总的数据条数
	@Override
	public int totalSize(Employee employee) {
		Session session=this.getSession(true);//得到session
		Criteria cri=session.createCriteria(Employee.class);//创建条件查询
		
		//模糊查询:【查询条件一定要和查询人员显示到页面的方法的查询条件一致，这样的分页才是想要数据的分页】
		if(employee!=null&&employee.getEmpName()!=null&&!"".equals(employee.getEmpName())){
			cri.add(Restrictions.ilike("empName", "%"+employee.getEmpName()+"%"));
		}else if (employee!=null&&employee.getTel()!=null&&!"".equals(employee.getTel())) {
			cri.add(Restrictions.ilike("tel", "%"+employee.getTel()+"%"));
		}
		//统计总条数
		cri.setProjection(Projections.rowCount());
		//执行
		List list=cri.list();	
		int nums=(int) list.get(0);
		session.close();
		return nums;
	}
}
