package com.zrgk.permission.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zrgk.permission.dao.EmployeeDaoInter;
import com.zrgk.permission.model.Emp_role;
import com.zrgk.permission.model.Employee;
import com.zrgk.permission.model.Menu;

//【继承了HibernateDaoSupport后，session可直接得到了】
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDaoInter{

	// 查询人员
	public List<Employee> queryEmployee(Employee employee) {
		Session session=this.getSession();//得到session
		Criteria cri=session.createCriteria(Employee.class);//创建条件查询
		
		//模糊查询
		if(employee!=null&&employee.getEmpName()!=null&&!"".equals(employee.getEmpName())){
			cri.add(Restrictions.ilike("empName", "%"+employee.getEmpName()+"%"));
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
		//查回角色，拼接字符串存进人员对象的属性里
		for(Employee e:list){
			String sql="select r.roleName from role r ,employee e, emp_role er where e.eid=er.eid and er.rid=r.rid and e.eid="+e.getEid();
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
				e.setStrRole(s);
			}
			
		}
		session.close();
		return list;		
	}

	//插入人员
	@Override
	public boolean insertEmployee(Employee employee) {
		Session session=this.getSession(true);//得到session
		Transaction tx=session.beginTransaction();//开启事务
		session.save(employee);
		tx.commit();//提交：不提交的话，数据库无值
		session.close();
		return false;
	}

	//得到最大的人员id，其实也就是当前插入的人员id，用于插入中间表用
	@Override
	public int getMaxId() {
		Session session=this.getSession();
		Criteria cri=session.createCriteria(Employee.class);
		cri.setProjection(Projections.max("eid"));//统计时，查回的是一行一列
		Employee emp=new Employee();
		cri.add(Example.create(emp));
		List list=cri.list();	
		int maxEid=(int) list.get(0);
		session.close();
		return maxEid;
	}
	
	//插中间表
	@Override
	public void insertEmpRole(Emp_role empRole) {
		Session session=this.getSession();
		Transaction tx=session.beginTransaction();
		
		session.save(empRole);
		
		tx.commit();
		session.close();
		
	}

	//登录查所有人员
	@Override
	public Employee login(Employee emp) {
		Session session=this.getSession(true);//得到session
		
		String sql="select * from employee  where empName=? and password=? ";
		Query query=session.createSQLQuery(sql).addEntity(Employee.class);
		query.setString(0, emp.getEmpName());//绑定问号
		query.setString(1, emp.getPassword());
		List<Employee> list=query.list();//查回数据
		Employee employee=null;
		if(list.size()>0){
			 employee=list.get(0);
		}
		session.close();
		return employee;		
	}

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
			}
		}
		session.close();
		return employee;
	}

	//修改人员修息
	@Override
	public void updateEmployee(Employee emp) {
		this.getHibernateTemplate().update(emp);
	/*	Session session=this.getSession(); 
		Transaction ts = session.beginTransaction();
		System.out.println(emp.getEmpName());
		session.update(emp);	
		ts.commit();
		session.close();*/
	}

	//按id删除人员
	@Override
	public void deleteEmployee(int eid) {
		Session session=this.getSession();
		Transaction ts=session.beginTransaction();
		Employee emp=new Employee();
		emp.setEid(eid);
		session.delete(emp);
		ts.commit();
		session.close();
	}

	//按人员id删除中间表
	@Override
	public void deleteEmp_role(int erid) {
		Session session=this.getSession();
		Transaction ts=session.beginTransaction();
		
		String hql="delete  Emp_role where eid=? ";	//这儿是操作的javabean	
		Query query = session.createQuery(hql);
		query.setInteger(0, erid);
			
		query.executeUpdate();//执行
		
		ts.commit();
		session.close();
	}
	
	//查询总的数据条数
	@Override
	public int totalSize(Employee employee) {
		Session session=this.getSession();//得到session
		Criteria cri=session.createCriteria(Employee.class);//创建条件查询
		
		//模糊查询:【查询条件一定要和查询人员显示到页面的方法的查询条件一致，这样的分页才是想要数据的分页】
		if(employee!=null&&employee.getEmpName()!=null&&!"".equals(employee.getEmpName())){
			cri.add(Restrictions.ilike("empName", "%"+employee.getEmpName()+"%"));
		}
		//统计总条数
		cri.setProjection(Projections.rowCount());
		//执行
		List list=cri.list();	
		int nums=(int) list.get(0);
		session.close();
		return nums;
	}

	//下面的多表联查方式，因查回的是object类型，所以需要调用sql的addEntity()方法塞进javabean里
	@Override
	public List<Menu> queryLoginMenu(int eid) {
		Session session=this.getSession();
		//distinct去重查询：因为一个人可能有多个角色，而角色之间可能有相同的菜单，所以要去重
		String sql="select distinct  m.mid,m.menunum,m.menuName, m.url,m.parentId,m.parentMenu,m.m_state from Employee e,Emp_role er,Role r,Role_menu rm,Menu m " +
				"where e.eid=er.eid and er.rid=r.rid and r.rid=rm.rid and rm.mid=m.mid and e.eid=? order by mid asc";
		Query query=session.createSQLQuery(sql).addEntity(Menu.class);
		 query.setInteger(0, eid);
		List<Menu> list=query.list();
		
		session.close();
		return list;
	}

	

}
