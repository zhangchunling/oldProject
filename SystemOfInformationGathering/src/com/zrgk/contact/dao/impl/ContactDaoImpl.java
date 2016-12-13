package com.zrgk.contact.dao.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zrgk.contact.dao.ContactDaoInter;
import com.zrgk.contact.model.My_Contact;
import com.zrgk.permission.model.Employee;
import com.zrgk.utils.Page;

/**
 * 通讯录Dao层实现类
 * @author samson
 * 2015-07-21
 */
public class ContactDaoImpl extends HibernateDaoSupport implements ContactDaoInter{

	@Override
	public void insertContact(My_Contact contact) {
		this.getHibernateTemplate().save(contact);
	}
	
	//模糊查询
		@Override
		public List<My_Contact> queryContact(My_Contact contact ,Page pa) {
			Criteria criteria=this.getSession().createCriteria(My_Contact.class);
			if (contact!=null&&contact.getCname()!=null&&!"".equals(contact.getCname().trim())) {
				criteria.add(Restrictions.like("cname", "%"+contact.getCname()+"%"));
			}else 
				if (contact!=null&&contact.getCphone()!=null&&!"".equals(contact.getCphone())) {
				criteria.add(Restrictions.like("cphone", "%"+contact.getCphone()+"%"));
			}
			int num = 1;
			num = (pa.getCurrentPage() - 1) * pa.getPageSize();
			criteria.setFirstResult(num);
			criteria.setMaxResults(pa.getPageSize());			
			List<My_Contact> list = criteria.list();
			return list;
		}
	
	//根据cid查询
	@Override
	public My_Contact queryContactById(int cid) {
		My_Contact contact =new My_Contact();
		contact = (My_Contact)getSession().get(My_Contact.class, cid);
		return contact;
	}
	
	//删除信息
	@Override
	public void deleteContact(My_Contact contact) {
		this.getHibernateTemplate().delete(contact);
		
	}
	//添加信息
	@Override
	public void updateContact(My_Contact contact) {
		this.getHibernateTemplate().update(contact);
		
	}
	//分页 个人通讯录总页
	@Override
	public int totalSize(My_Contact contact) {
		Criteria criteria=this.getSession().createCriteria(My_Contact.class);
		if (contact!=null&&contact.getCname()!=null&&!"".equals(contact.getCname().trim())) {
			criteria.add(Restrictions.like("cname", "%"+contact.getCname()+"%"));
		}else 
			if (contact!=null&&contact.getCphone()!=null&&!"".equals(contact.getCphone())) {
			criteria.add(Restrictions.like("cphone", "%"+contact.getCphone()+"%"));
		}
		//统计总条数
		criteria.setProjection(Projections.rowCount());
		//执行
		List list=criteria.list();	
		int nums=(int) list.get(0);
		
		return nums;
	}


	
}