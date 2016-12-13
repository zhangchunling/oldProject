package com.zrgk.permission.dao;

import java.util.List;
import com.zrgk.permission.model.Menu;

public interface MenuDaoInter {

	//1.添加菜单
	void insertMenu(Menu menu);
	//2.在菜单管里时 查询菜单
	List<Menu> queryMenus(Menu menu);
	//3.按id删
	void deleteMenu(Menu menu);
	//4.修改
	void updateMenu(Menu menu);
	//5.根据Id查询
	Menu queryById(int id);
	//1.在角色管理里时 查所有菜单
	List<Menu> queryAllMenu();
	//根据父id查回父菜单
	Menu queryByParentId(int pid);
	//查询总的数据条数
	int totalSize(Menu menu);
	
	
	/*//2.根据菜单的id查找菜单的名称，该名称作为要添加的菜单的父菜单的名称
		Menu selectMenuById(int id);
	//3.在添加菜单资源的时�?，点击保存，将新添加的菜单添加到菜单表里�?
	void insertMenu(Menu menu);
	//4.在编辑页面里，当修改完所有的信息后，把修改后的信息更新到菜单�?
	void updateMenuByMid(Menu menu);
	//5.在菜单资源主页面里点击删除执行的操作
	void deleteMenuById(int mid);
	//6.在点击禁用的时�?，根据rid将�?中的角色的状态改为禁�?
	void updateForbidMenu(int mid);
	//7.根据eid查询出该eid�?��的菜�?
	List<Menu> selectMenuByEid(int eid);*/
}
