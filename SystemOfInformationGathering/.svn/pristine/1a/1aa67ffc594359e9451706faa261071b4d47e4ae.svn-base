package com.zrgk.permission.service;

import java.util.List;

import com.zrgk.permission.model.Emp_role;
import com.zrgk.permission.model.Role;
import com.zrgk.permission.model.Role_menu;

public interface RoleServiceInter {
	//添加人员时查所有角色
	List<Role> queryAllRole();
	
	//在角色管理里查所有角色
	List<Role> queryRoles(Role role);
	
	//插入角色
	void insertRole(Role role);
	
	//3.获得当前最大的rid
	int getMaxId();
	
	//插入角色-菜单中间表
	void insertRole_menu(Role_menu rm);
	
	//根据角色id查询
	Role queryRoleById(int id);
	
	//更新角色
	void updateRole(Role role);
		
	//根据Id删除
	void delelteRoleById(int rid);
	
	//根据id删除角色-菜单中间表
	void deleteRole_menu(int rmid);
		
	//查询总的数据条数
	int totalSize(Role role);
}
