package com.zrgk.manu.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zrgk.manu.bean.Manu;
import com.zrgk.manu.bean.Manu_User;
import com.zrgk.manu.bean.PageFile;
import com.zrgk.manu.dao.MyManuDaoInter;
import com.zrgk.permission.model.Employee;

public class MyManuDaoImpl extends HibernateDaoSupport implements
		MyManuDaoInter {

	@Override
	// public List<Manu> queryMyManuByUid(Manu mu) {
	//
	// DetachedCriteria cri = DetachedCriteria.forClass(Manu.class);
	// if(mu.getMname()!=null&&!"".equals(mu.getMname().trim())){
	// cri.add(Restrictions.like("mname",mu.getMname(),MatchMode.ANYWHERE));
	// }
	// if(mu.getMbknum()!=null){
	// cri.add(Restrictions.like("mbknum",mu.getMbknum()+"",MatchMode.ANYWHERE));
	// }
	// if(mu.getMstartTime()!=null&&!"".equals(mu.getMstartTime().trim())){
	// cri.add(Restrictions.like("mstartTime",mu.getMstartTime(),MatchMode.ANYWHERE));
	// }
	// if(mu.getMendTime()!=null&&!"".equals(mu.getMendTime().trim())){
	// cri.add(Restrictions.like("mendTime",mu.getMendTime(),MatchMode.ANYWHERE));
	// }
	// if(mu.getMmaster()!=null&&!"".equals(mu.getMmaster().trim())){
	// cri.add(Restrictions.like("mmaster",mu.getMmaster(),MatchMode.ANYWHERE));
	// }
	//
	// cri.addOrder(Order.desc("mid"));
	// int firstResult=0;
	// if(mu.getCurrentPage()>1){
	// firstResult=(mu.getCurrentPage()-1)*mu.getPageSize();
	// System.out.println("firstResult"+firstResult);
	// }
	// List<Manu> mus=null;
	// this.getSession().createSQLQuery("select * from manu where mid=");
	// mus=this.getHibernateTemplate().findByCriteria(cri,firstResult,mu.getPageSize());
	// // List<PageFile> list=this.getHibernateTemplate().findByExample(pf, 0,
	// pf.getPageSize());
	// return mus;
	//
	// }
	public boolean deleteMyManuByID(Manu mu) {
		return false;
	}

	@Override
	public Manu queryMyManuByID(int id) {

		return (Manu) this.getHibernateTemplate()
				.find("from Manu where mid=" + id).get(0);
	}

	@Override
	public Integer queryCountByManu(Manu mu) {
		return null;
	}

	public List<Manu> queryMyManu(Manu mu, int uid, String accept) {
		List<Manu> mas = new ArrayList<Manu>();
		StringBuffer sql = new StringBuffer();

		sql.append("select m.mid,m.mname,m.mbknum,m.minfo,m.mstarttime,m.mendtime,m.mstate,m.mmaster,a.umstate from manu m,(select mus.mid,mus.umstate from manu_user mus where mus.uuid="
				+ uid
				+ ") a where a.mid=m.mid and a.umstate"
				+ accept
				+ " and m.mstate>=1");
		if (mu.getMname() != null && !"".equals(mu.getMname().trim())) {

			sql.append(" and m.mname like '%" + mu.getMname() + "%'");
		}
		if (mu.getMbknum() != null) {
			sql.append(" and m.mbknum like '%" + mu.getMbknum() + "%'");
		}
		if (mu.getMstartTime() != null && !"".equals(mu.getMstartTime().trim())) {
			sql.append(" and m.mstarttime like '%" + mu.getMstartTime() + "%'");
		}
		if (mu.getMendTime() != null && !"".equals(mu.getMendTime().trim())) {
			sql.append(" and m.mendtime like '%" + mu.getMendTime() + "%'");
		}
		if (mu.getMmaster() != null && !"".equals(mu.getMmaster().trim())) {
			sql.append(" and m.mmaster like '%" + mu.getMmaster() + "%'");
		}
		// .sql语句查询的结果不能直接转换成相应对象链表
		// 这里createSqlQuery后面你一定要加上addEntity(class) 不加接收类class的话不能直接转换成相应对象链表
		Session ses=this.getSession();
		Query query = ses.createSQLQuery(sql.toString());
		int firstResult = 0;

		if (mu.getCurrentPage() > 1) {
			firstResult = (mu.getCurrentPage() - 1) * mu.getPageSize();
		}
		sql.append(" order by m.mid desc ");
		System.out.println("*"+sql.toString()+"*");
		query.setFirstResult(firstResult);
		query.setMaxResults(mu.getPageSize());
		List oldList = query.list();
		Iterator li = oldList.iterator();
//拼装多表查询结果集
		while (li.hasNext()) {
			Manu tempMu = new Manu();
			Object[] obs = (Object[]) li.next();
			tempMu.setMid(Integer.parseInt(obs[0]+""));
			tempMu.setMname(obs[1]+"");
			tempMu.setMbknum(Integer.parseInt(obs[2]+""));
			tempMu.setMinfo(obs[3]+"");
			tempMu.setMstartTime(obs[4]+"");
			tempMu.setMendTime(obs[5]+"");
			tempMu.setMstate(Integer.parseInt((obs[6]+"")));
			tempMu.setMmaster(obs[7]+"");
			tempMu.setUmstate(Integer.parseInt(obs[8]+""));
			mas.add(tempMu);
			
		}
		ses.close();
		return mas;
	}

	@Override
	public Manu_User queryStateByUidAndMid(Manu_User muu) {
		Manu_User m = new Manu_User();
		Session session = this.getSession();
		m = (Manu_User) session
				.createQuery(
						"from Manu_User where mid=" + muu.getMid()
								+ " and uuid=" + muu.getUuid()).list().get(0);
		session.close();
		return m;
	}

	@Override
	public void updateStateById(Manu_User muu) {
		this.getHibernateTemplate().update(muu);
	}

	@Override
	public Integer queryMyManuCount(Manu mu, int uid, String accept) {
		Integer count = 0;
		List<Manu> mas = new ArrayList<Manu>();
		StringBuffer sql = new StringBuffer();

		sql.append("select count(*) from manu m,(select mus.mid,mus.umstate from manu_user mus where mus.uuid="
				+ uid
				+ ") a where a.mid=m.mid and a.umstate"
				+ accept
				+ " and m.mstate>=1");
		if (mu.getMname() != null && !"".equals(mu.getMname().trim())) {
			sql.append(" and m.mname like '%" + mu.getMname() + "%'");
		}
		if (mu.getMbknum() != null) {
			sql.append(" and m.mbknum like '%" + mu.getMbknum() + "%'");
		}
		if (mu.getMstartTime() != null && !"".equals(mu.getMstartTime().trim())) {
			sql.append(" and m.mstarttime like '%" + mu.getMstartTime() + "%'");
		}
		if (mu.getMendTime() != null && !"".equals(mu.getMendTime().trim())) {
			sql.append(" and m.mendtime like '%" + mu.getMendTime() + "%'");
		}
		if (mu.getMmaster() != null && !"".equals(mu.getMmaster().trim())) {
			sql.append(" and m.mmaster like '%" + mu.getMmaster() + "%'");
		}
		// .sql语句查询的结果不能直接转换成相应对象链表
		// 这里createSqlQuery后面你一定要加上addEntity(class) 不加接收类class的话不能直接转换成相应对象链表
		Session session=this.getSession();
		Query query = session.createSQLQuery(sql.toString());
		count = Integer.parseInt(query.list().get(0) + "");
		session.close();
		return count;
	}

	@Override
	public List<Manu> queryAllManu(int uid, int accept) {
		System.out.println("111");
		Integer count = 0;
		List<Manu> mas = new ArrayList<Manu>();
		StringBuffer sql = new StringBuffer();

		sql.append("select * from manu m,(select mus.mid,mus.umstate  from manu_user mus where mus.uuid="
				+ uid
				+ ") a where a.mid=m.mid and a.umstate="
				+ accept
				+ " and m.mstate=1");
		// .sql语句查询的结果不能直接转换成相应对象链表
		// 这里createSqlQuery后面你一定要加上addEntity(class) 不加接收类class的话不能直接转换成相应对象链表
		Session session=this.getSession();
		Query query = session.createSQLQuery(sql.toString())
				.addEntity(Manu.class);
		mas = query.list();
		for (Manu manu : mas) {
			System.out.println(manu);
		}
		session.close();

		return mas;
	}

	@Override
	public List<Manu> queryNotExitsPageFileOfManus(int uid) {
		List <Manu> mus=null;
		Session session=this.getSession();
		Query query=null;
			query=session.createSQLQuery("select m.mid,m.mname,m.mbknum,m.minfo,m.mstarttime,m.mendtime,m.mstate,m.mmaster,a.umstate from manu m,(select mus.mid,mus.umstate,mus.pfid from manu_user mus where mus.uuid="+uid+") a where a.mid=m.mid and a.umstate=1 and a.pfid=0").addEntity(Manu.class);
		mus=query.list();
		session.close();
		return mus;
	}
	@Override
	public boolean queryManuIsPf(Manu_User muu,int uid) {
		Session session=this.getSession();
		List list=null;
		Query query=null;
			query=session.createSQLQuery("select * from manu_user mus where mus.uuid="+uid+" and mus.mid="+muu.getMid()+" and mus.pfid!=0").addEntity(Manu_User.class);
			list=query.list();
			if(list.size()!=0){
				return true;
			}
			session.close();
		return false;
	}

	@Override
	public Manu_User queryManu_UserByUidAndMid(Manu_User muu) {
		List <Manu_User> list=this.getHibernateTemplate().find("from Manu_User where mid="+muu.getMid()+" and uuid="+muu.getUuid());
		return list.get(0);
	}

	@Override
	public void updateManu_UserPfidByMuid(Manu_User muu) {
		System.out.println("1111"+muu);
		this.getHibernateTemplate().update(muu);
		
	}

	@Override
	public List<Manu> queryManu(Manu mu,String iscount) {
		List<Manu> mas = new ArrayList<Manu>();
		StringBuffer hql = new StringBuffer();
		hql.append("from Manu where mmaster=").append("'"+mu.getMmaster()+"'").append(" and mstate between 0 and 1").append(" and 1=1 ");
				
		if (mu.getMname() != null && !"".equals(mu.getMname().trim())) {

			hql.append(" and mname like '%" + mu.getMname() + "%'");
		}
		if (mu.getMbknum() != null) {
			hql.append(" and mbknum like '%" + mu.getMbknum() + "%'");
		}
		if (mu.getMstartTime() != null && !"".equals(mu.getMstartTime().trim())) {
			hql.append(" and mstartTime like '%" + mu.getMstartTime() + "%'");
		}
		if (mu.getMendTime() != null && !"".equals(mu.getMendTime().trim())) {
			hql.append(" and mendTime like '%" + mu.getMendTime() + "%'");
		}
		Query query = this.getSession().createQuery(hql.toString());
		int firstResult = 0;

		if (mu.getCurrentPage() > 1) {
			firstResult = (mu.getCurrentPage() - 1) * mu.getPageSize();
		}
		hql.append(" order by mid desc ");
		if("true".equals(iscount)){//返回所有符合条件的 用于与ACTION 获得COUNT
			mas=query.list();
			System.out.println("mas size--"+mas.size());
			return mas;
		}else{
		query.setFirstResult(firstResult);
		query.setMaxResults(mu.getPageSize());
		mas= query.list();
		return mas;
		}
	}

	@Override
	public void updateMstateByMid(Manu mu) {
		this.getHibernateTemplate().update(mu);
		
	}

	@Override
	public Manu queryManuById(Manu mu) {
		return (Manu)this.getHibernateTemplate().find("from Manu where mid="+mu.getMid()).get(0);
	}

	@Override
	public List<Manu> queryWaitManu(Manu mu,String iscount) {
		List<Manu> mas = new ArrayList<Manu>();
		StringBuffer hql = new StringBuffer();
		hql.append("from Manu where mmaster=").append("'"+mu.getMmaster()+"'").append(" and mstate between 1 and 3").append(" and 1=1 ");
				
		if (mu.getMname() != null && !"".equals(mu.getMname().trim())) {

			hql.append(" and mname like '%" + mu.getMname() + "%'");
		}
		if (mu.getMbknum() != null) {
			hql.append(" and mbknum like '%" + mu.getMbknum() + "%'");
		}
		if (mu.getMstartTime() != null && !"".equals(mu.getMstartTime().trim())) {
			hql.append(" and mstartTime like '%" + mu.getMstartTime() + "%'");
		}
		if (mu.getMendTime() != null && !"".equals(mu.getMendTime().trim())) {
			hql.append(" and mendTime like '%" + mu.getMendTime() + "%'");
		}
		Session session=this.getSession();
		Query query = session.createQuery(hql.toString());
		int firstResult = 0;

		if (mu.getCurrentPage() > 1) {
			firstResult = (mu.getCurrentPage() - 1) * mu.getPageSize();
		}
		hql.append(" order by mid desc ");
		
		if("true".equals(iscount)){//返回所有符合条件的 用于与ACTION 获得COUNT
			mas=query.list();
			System.out.println("mas size--"+mas.size());
			return mas;
		}else{
		query.setFirstResult(firstResult);
		query.setMaxResults(mu.getPageSize());
		mas= query.list();
		session.close();
		return mas;
		}
		
	}
	@Override
	public List<Manu> queryOkedManu(Manu mu,String iscount) {
		List<Manu> mas = new ArrayList<Manu>();
		StringBuffer hql = new StringBuffer();
		hql.append("from Manu where mmaster=").append("'"+mu.getMmaster()+"'").append(" and mstate>1").append(" and 1=1 ");
				
		if (mu.getMname() != null && !"".equals(mu.getMname().trim())) {

			hql.append(" and mname like '%" + mu.getMname() + "%'");
		}
		if (mu.getMbknum() != null) {
			hql.append(" and mbknum like '%" + mu.getMbknum() + "%'");
		}
		if (mu.getMstartTime() != null && !"".equals(mu.getMstartTime().trim())) {
			hql.append(" and mstartTime like '%" + mu.getMstartTime() + "%'");
		}
		if (mu.getMendTime() != null && !"".equals(mu.getMendTime().trim())) {
			hql.append(" and mendTime like '%" + mu.getMendTime() + "%'");
		}
		Session session=this.getSession();
		Query query = session.createQuery(hql.toString());
		int firstResult = 0;

		if (mu.getCurrentPage() > 1) {
			firstResult = (mu.getCurrentPage() - 1) * mu.getPageSize();
		}
		hql.append(" order by mid desc ");
		
		if("true".equals(iscount)){//返回所有符合条件的 用于与ACTION 获得COUNT
			mas=query.list();
			System.out.println("mas size--"+mas.size());
			return mas;
		}else{
		query.setFirstResult(firstResult);
		query.setMaxResults(mu.getPageSize());
		mas= query.list();
		session.close();
		return mas;
		}
	}

	public List<Manu> queryGGManu(Manu mu) {
		List<Manu> mas = new ArrayList<Manu>();
		StringBuffer hql = new StringBuffer();
		Session s=this.getSession();
		hql.append("from Manu where mmaster=").append("'"+mu.getMmaster()+"'").append(" and mstate>1").append(" and 1=1 ");
				
		if (mu.getMname() != null && !"".equals(mu.getMname().trim())) {

			hql.append(" and mname like '%" + mu.getMname() + "%'");
		}
		if (mu.getMbknum() != null) {
			hql.append(" and mbknum like '%" + mu.getMbknum() + "%'");
		}
		if (mu.getMstartTime() != null && !"".equals(mu.getMstartTime().trim())) {
			hql.append(" and mstartTime like '%" + mu.getMstartTime() + "%'");
		}
		if (mu.getMendTime() != null && !"".equals(mu.getMendTime().trim())) {
			hql.append(" and mendTime like '%" + mu.getMendTime() + "%'");
		}
		Query query = s.createQuery(hql.toString());
		int firstResult = 0;

		if (mu.getCurrentPage() > 1) {
			firstResult = (mu.getCurrentPage() - 1) * mu.getPageSize();
		}
		hql.append(" order by mid desc ");
		System.out.println(hql);
		query.setFirstResult(firstResult);
		query.setMaxResults(mu.getPageSize());
		mas= query.list();
		System.out.println("----"+mas.size());
		s.close();
		return mas;
	}
	
	@Override
	public void insertManu(Manu mu) {
			this.getHibernateTemplate().save(mu);
	}

	@Override
	public void insertManu_User(Manu_User muu) {
		
		this.getHibernateTemplate().save(muu);
	}

	@Override
	public List<Employee> queryAllEmp() {
		return this.getHibernateTemplate().find("from Employee");
	}

	@Override
	public void updatePfStateById(PageFile pf) {
		this.getHibernateTemplate().update(pf);
	}

	@Override
	public PageFile queryPageFileById(PageFile pf) {
		return (PageFile)this.getHibernateTemplate().find("from PageFile where pf_id="+pf.getPf_id()).get(0);
	}



	

}
