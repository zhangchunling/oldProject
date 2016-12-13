package com.zrgk.permission.service.impl;

import java.util.List;

import com.zrgk.permission.dao.MenuDaoInter;
import com.zrgk.permission.model.Menu;
import com.zrgk.permission.service.MenuServiceInter;

public class MenuServiceImpl implements MenuServiceInter{

	private MenuDaoInter menuDao;//声明一个存dao层接口的属性

	//1.添加菜单
	@Override
	public void insertMenu(Menu menu) {
		menuDao.insertMenu(menu);
	}

	//2.在菜单管理时查询所有
	@Override
	public List<Menu> queryMenus(Menu menu) {
		List list=menuDao.queryMenus(menu);
		return list;
	}
	
	//3.删除
	@Override
	public void deleteMenu(Menu menu) {
		menuDao.deleteMenu(menu);		
	}

	//修改
	@Override
	public void updateMenu(Menu menu) {
		menuDao.updateMenu(menu);
	}

	//根据Id删除
	@Override
	public Menu queryById(int id) {
		Menu menu=menuDao.queryById(id);
		return menu;
	}
	
	//查所有角色
	@Override
	public List<Menu> queryAllMenu() {
		List list=menuDao.queryAllMenu();
		return list;
	}

	//根据父id查回父菜单
	@Override
	public Menu queryByParentId(int pid) {
		Menu menu=menuDao.queryByParentId(pid);
		return menu;
	}

	//查询数据条数，分页用
	@Override
	public int totalSize(Menu menu) {
		int nums=menuDao.totalSize(menu);
		return nums;
	}	
		
	public MenuDaoInter getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(MenuDaoInter menuDao) {
		this.menuDao = menuDao;
	}

	
	

	

}
