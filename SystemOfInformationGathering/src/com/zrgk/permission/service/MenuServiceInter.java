package com.zrgk.permission.service;

import java.util.List;
import com.zrgk.permission.model.Menu;

public interface MenuServiceInter {
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
	//查所有角色
	List<Menu> queryAllMenu();
	//根据父id查回父菜单
	Menu queryByParentId(int pid);
	//查询总的数据条数
	int totalSize(Menu menu);
}
