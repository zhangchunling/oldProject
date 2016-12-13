package com.zrgk.email.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zrgk.email.model.Email;
import com.zrgk.email.model.Receive;
import com.zrgk.permission.model.Employee;
import com.zrgk.utils.Page;

public class EmailDaoImpl extends HibernateDaoSupport implements EmailDao {

	public void save(Email e) {

		this.getHibernateTemplate().save(e);
	}
//	得到人员报employee所有人员的名字的方法
	public List<Employee> queryAllEmp() {

		return this.getHibernateTemplate().find("from Employee");

	}
//		通过id查询email表的语句的方法
	@Override
	public Email getEmailId(Email email) {

		Email e = null;
		List<Email> list = this.getHibernateTemplate().findByExample(email);

		
		if (list.size() > 0) {
			e = list.get(0);
		}
		

		return e;
	}

	@Override
	public void addReceive(Receive r) {

		this.getHibernateTemplate().save(r);

	}
//	收件箱主页面的查询语句方法
	@Override
	public List getGiveemail(Page p, Email e) {
		List<Email> list = new ArrayList<>();
		Session s = this.getSession();

		Transaction t = s.beginTransaction();

		Criteria cri = s.createCriteria(Email.class);
		// 根据用户名条件查询我的所有信息
		if (e != null && e.getSend() != null && !"".equals(e.getSend().trim())) {

			cri.add(Restrictions.eq("send", e.getSend()));
		}

		// 根据发送时间排序的方法
		if (e != null && e.getSendTime() != null
				&& !"".equals(e.getSendTime().trim())) {

			cri.addOrder(Order.desc("sendTime"));
		}
		// ===============分页的代码=======================

		int num = 1;
		num = (p.getCurrentPage() - 1) * p.getPageSize();
		cri.setFirstResult(num);
		cri.setMaxResults(p.getPageSize());

		// ==============模糊查询lick==============================
		// 根据标题进行模糊查询的方法
		if (e != null && e.getTitle() != null
				&& !"".equals(e.getTitle().trim())) {

			cri.add(Restrictions.like("title", "%" + e.getTitle() + "%"));
		}

		list = cri.list();
		t.commit();

		s.close();
		return list;
	}
//	通过id删除一条数据的方法
	@Override
	public void deleteById(Integer id) {

		Email e = new Email();

		e.setId(id);
		 
 		try {
 			this.getHibernateTemplate().delete(e);
 		} catch (Exception e2) {
 			e2.printStackTrace();
 		
 	}
	 
		
	}
//	通过id查询这条语句的若有数据的方法
	@Override
	public Email queryById(Integer id) {

		Email list = (Email) this.getHibernateTemplate()
				.find("from Email where id =" + id + "").get(0);
		System.out.println("dao层中的list===" + list.getAccessory()
				+ list.getReceive());
		return list;
	}
//		收件箱的总条数的方法
	@Override
	public int totalSize(String send) {
		Session s = this.getSession();
		Criteria cri = s.createCriteria(Email.class);

		int total = (int) cri.setProjection(Projections.rowCount()).list()
				.get(0);

		System.out.println("total=" + total);
		return total;

	}
//		导出export的查询方法
	@Override
	public List<Email> ExportQueryAll(String send) {
		System.out.println(send);
		Email e = new Email();

		e.setSend(send);
		List<Email> list = this.getHibernateTemplate().findByExample(e);
		return list;
	}
//		收件箱的主页面的查询方法
	@Override
	public List<Email> QueryBySend(Email e, Page p) {

		Session s = this.getSession();

		String str = " select e.* from Receive r,Email e where  e.id = r.rid and r.rname ='"
				+ e.getSend() + "'  ";

		StringBuffer sql = new StringBuffer(str);

		if (e != null && e.getTitle() != null && !"".equals(e.getTitle())) {
			sql.append(" and   e.title  like  '%" + e.getTitle() + "%' ");

		}
		if (e != null && e.getSendTime() != null && !"".equals( e.getSendTime() )) {
			sql.append("  order  by   e.sendTime  desc ");

		}

		Query query = s.createSQLQuery(sql.toString()).addEntity(Email.class);

		if (p != null && p.getCurrentPage() >0 && !"".equals(p.getCurrentPage())) {

			int num1 = (p.getCurrentPage() - 1) * p.getPageSize();
			int num2 = p.getPageSize() ;

			query.setFirstResult(num1);
			query.setMaxResults(num2);

		}

		
		List<Email> list = query.list();
		s.close();
		return list;
	}
//	收件箱的总条数的方法
	@Override
	public int totalSize2(String send) {
		Session s = this.getSession();

		String str = "select * from Receive   where rname = '" + send + "'";
		Query query = s.createSQLQuery(str);

		List list = query.list();
		System.out.println(list);
		System.out.println("total=" + list.size());
		s.close();
		return list.size();
	}
//		修改状态的方法
	@Override
	public void upState(Email e) {
		
 		 Session session = this.getSession();
			Query query = session.createSQLQuery("update  Email set state ="+e.getState()+"  where id ="+e.getId()); 
			query.executeUpdate();
			session.close();
		   
	}
	@Override
	public void deleteByIdReceive(int id) {
		
		Receive r = new Receive();
		Session sess=this.getSession();
		r.setRid(id);
		String sql="delete  from receive where rid ="+r.getRid();
		 Query query=sess.createSQLQuery(sql);
		query.executeUpdate();
		sess.close();
		  
	}

}
