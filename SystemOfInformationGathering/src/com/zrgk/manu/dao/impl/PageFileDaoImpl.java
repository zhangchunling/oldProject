package com.zrgk.manu.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zrgk.manu.bean.Manu;
import com.zrgk.manu.bean.Manu_User;
import com.zrgk.manu.bean.PageFile;
import com.zrgk.manu.dao.PageFileDaoInter;

public class PageFileDaoImpl extends HibernateDaoSupport implements PageFileDaoInter {

	@Override
	public boolean insertPageFile(PageFile pf) {
			this.getHibernateTemplate().save(pf);
		return true;
	}

	@Override
	public List<PageFile> queryPageFile(PageFile pf) {
		
		DetachedCriteria cri = DetachedCriteria.forClass(PageFile.class);
		if(pf.getPf_type()!=null&&!"".equals(pf.getPf_type().trim())){
			cri.add(Restrictions.like("pf_type",pf.getPf_type(),MatchMode.ANYWHERE));
		}
		if(pf.getPf_style()!=null&&!"".equals(pf.getPf_style().trim())){
			cri.add(Restrictions.like("pf_style",pf.getPf_style(),MatchMode.ANYWHERE));
		}
		if(pf.getPf_name()!=null&&!"".equals(pf.getPf_name().trim())){
		cri.add(Restrictions.like("pf_name",pf.getPf_name(),MatchMode.ANYWHERE));
		}
		cri.addOrder(Order.desc("pf_id"));
		int firstResult=0;
		if(pf.getCurrentPage()>1){
			firstResult=(pf.getCurrentPage()-1)*pf.getPageSize();
			System.out.println("firstResult"+firstResult);
		}
		List<PageFile> pfs=null;
		 pfs=this.getHibernateTemplate().findByCriteria(cri,firstResult,pf.getPageSize());
		//		List<PageFile> list=this.getHibernateTemplate().findByExample(pf, 0, pf.getPageSize());
		return pfs;
	}
	
	@Override
	public boolean deletePageFileByID(PageFile pf) {
		this.getHibernateTemplate().delete(pf);
		return true;
	}

	@Override
	public PageFile queryPageFileByID(PageFile pf) {
		return  (PageFile)getHibernateTemplate().find("from PageFile where pf_id="+pf.getPf_id()).get(0);
	}

	@Override
	public Integer queryCountByPageFile(PageFile pf) {
		Integer count=null;
		DetachedCriteria cri = DetachedCriteria.forClass(pf.getClass());
		if(pf.getPf_type()!=null&&!"".equals(pf.getPf_type().trim())){
		cri.add(Restrictions.like("pf_type",pf.getPf_type(),MatchMode.ANYWHERE));
		}
		if(pf.getPf_style()!=null&&!"".equals(pf.getPf_style().trim())){
		cri.add(Restrictions.like("pf_style",pf.getPf_style(),MatchMode.ANYWHERE));
		}
		if(pf.getPf_name()!=null&&!"".equals(pf.getPf_name().trim())){
		cri.add(Restrictions.like("pf_name",pf.getPf_name(),MatchMode.ANYWHERE));
		}
		count=getHibernateTemplate().findByCriteria(cri).size();
		return count;
	}

	@Override
	public List<PageFile> queryPageFile() {
		return this.getHibernateTemplate().find("from PageFile order by pf_id asc");
	}

	@Override
	public PageFile queryPageFileByPUID(PageFile pf) {
		//如果查询结果为空 则返回null
		List list=this.getHibernateTemplate().find("from PageFile where pf_mu_id="+pf.getPf_mu_id()+" and pf_author='"+pf.getPf_author()+"'");
		if(list.size()!=0){
			return (PageFile)list.get(0);
		}
		return null;
		}
	public List<Manu> queryNotExitsPageFileOfManus(int uid) {
		List <Manu> mus=null;
		Session session=this.getSession();
		Query query=session.createSQLQuery("select m.mid,m.mname,m.mbknum,m.minfo,m.mstarttime,m.mendtime,m.mstate,m.mmaster,a.umstate from manu m,(select mus.mid,mus.umstate,mus.pfid from manu_user mus where mus.uuid="+uid+") a where a.mid=m.mid and a.umstate=1 and a.pfid=0").addEntity(Manu.class);
		mus=query.list();
		session.close();
		return mus;
	}

	@Override
	public PageFile queryPageFileByPF_mu_id(PageFile pf) {
		Session session=this.getSession();
		Query query=session.createQuery("from PageFile where pf_mu_id="+pf.getPf_mu_id());
		List<PageFile> list=query.list();
		System.out.println(list.size());
		return pf;
	}
	//查询待审核一审二审稿件
	@Override
	public List<PageFile> queryWaitPageFile(PageFile pf,String auditNum,String iscount ) {
		List<PageFile>pfs=null;
		StringBuffer sql = new StringBuffer();
		Session s=this.getSession();
		sql.append("select * from pageFile pf,(select * from manu_user where umstate").append(auditNum).append(" and mid=").append(pf.getPf_mu_id()).append(")ls where pf.pf_id=ls.pfid and 1=1");
				
		if (pf.getPf_type() != null && !"".equals(pf.getPf_type().trim())) {

			sql.append(" and pf_type like '%" + pf.getPf_type() + "%'");
		}
		if (pf.getPf_style() != null&& !"".equals(pf.getPf_style().trim())) {
			sql.append(" and pf_style like '%" + pf.getPf_style() + "%'");
		}
		if (pf.getPf_name() != null && !"".equals(pf.getPf_name().trim())) {
			sql.append(" and pf_name like '%" + pf.getPf_name() + "%'");
		}
		Query query = s.createSQLQuery(sql.toString()).addEntity(PageFile.class);
		int firstResult = 0;
		
		if (pf.getCurrentPage() > 1) {
			firstResult = (pf.getCurrentPage() - 1) * pf.getPageSize();
		}
		sql.append(" order by pf_createtime asc ");
		
		if("true".equals(iscount)){
			pfs= query.list();
			return pfs;
		}else{
		query.setFirstResult(firstResult);
		query.setMaxResults(pf.getPageSize());
		pfs= query.list();
		s.close();
		return pfs;
		}
	}

	@Override
	public void updatePageFilePf_mu_id(PageFile pf) {
		this.getHibernateTemplate().update(pf);
		
	}

	@Override
	public void updatePageFile(PageFile pf) {
		this.getHibernateTemplate().update(pf);
		
	}


}
