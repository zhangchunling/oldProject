package com.zrgk.permission.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zrgk.permission.dao.MenuDaoInter;
import com.zrgk.permission.model.Employee;
import com.zrgk.permission.model.Menu;


public class MenuDaoImpl extends HibernateDaoSupport implements MenuDaoInter{

	//1.添加菜单
	@Override
	public void insertMenu(Menu menu) {
		Session session=this.getSession(true);//得到session
		Transaction tx=session.beginTransaction();//开启事务
		
		session.save(menu);
		
		tx.commit();//提交：不提交的话，数据库无值
		session.close();
	}
	
	//2.在菜单管理时 查询所有
	@Override
	public List<Menu> queryMenus(Menu menu) {
		//List ls=this.getHibernateTemplate().find("from Menu");//使用模板查询		
		Session session=this.getSession();//得到session
		Criteria cri=session.createCriteria(Menu.class);//创建条件查询
		//模糊查询
		if(menu!=null&&menu.getMenuName()!=null&&!"".equals(menu.getMenuName())){
			cri.add(Restrictions.ilike("menuName", "%"+menu.getMenuName()+"%"));
		}
		//按Id倒序排
		cri.addOrder(Order.desc("mid"));
		//分页，从第几条数据开始取
		if(menu!=null){
			cri.setFirstResult((menu.getCurrentPage()-1)*5);
			cri.setMaxResults(5);
		}		
		//执行
		List<Menu> list=cri.list();	
		session.close();
		return list;
	}
	
	//删除
	@Override
	public void deleteMenu(Menu menu) {
		this.getHibernateTemplate().delete(menu);
	}
	
	//修改
	@Override
	public void updateMenu(Menu menu) {
		System.out.println(menu.getMid()+"--"+menu.getMenuName()+"--"+menu.getM_state()+"--"+menu.getUrl());
		Session session=this.getSession();
		Transaction ts=session.beginTransaction();
		session.update(menu);//怎么只有update不行呢
		
		ts.commit();
		session.close();		
	}

	//根据id查询
	@Override
	public Menu queryById(int id) {
		Session session=this.getSession();
		//用get方法通过Id查询
		Menu menu=(Menu) session.get(Menu.class,id);
		session.close();
		return menu;
	}
	
	//添加角色时查询的有菜单
	@Override
	public List<Menu> queryAllMenu() {
		Session session=this.getSession();//得到session
		Criteria cri=session.createCriteria(Menu.class);//创建条件查询
		Menu menu=new Menu();
		//添加角色时，状态为禁用的不能查回来，只能查回启用的
		cri.addOrder(Order.asc("mid"));
		cri.add(Restrictions.eq("m_state",1));
		//执行
		List<Menu> list=cri.list();	
		session.close();
		return list;
	}

	//查询数据总条数
	@Override
	public int totalSize(Menu menu) {
		Session session=this.getSession(true);//得到session
		Criteria cri=session.createCriteria(Menu.class);//创建条件查询
		
		//模糊查询:【查询条件一定要和查询人员显示到页面的方法的查询条件一致，这样的分页才是想要数据的分页】
		if(menu!=null&&menu.getMenuName()!=null&&!"".equals(menu.getMenuName())){
			cri.add(Restrictions.ilike("menuName", "%"+menu.getMenuName()+"%"));
		}
		//统计总条数
		cri.setProjection(Projections.rowCount());
		//执行
		List list=cri.list();	
		int nums=(int) list.get(0);
		session.close();
		return nums;
	}

	//根据父id查回父菜单
	@Override
	public Menu queryByParentId(int pid) {
		Session session=this.getSession();
		//用get方法通过Id查询
		Menu menu=(Menu) session.get(Menu.class,pid);	
		session.close();
		return menu;
	}

	
}
