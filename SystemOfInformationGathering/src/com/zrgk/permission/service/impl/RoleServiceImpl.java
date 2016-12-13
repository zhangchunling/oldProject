package com.zrgk.permission.service.impl;

import java.util.List;

import com.zrgk.permission.dao.RoleDaoInter;
import com.zrgk.permission.model.Role;
import com.zrgk.permission.model.Role_menu;
import com.zrgk.permission.service.RoleServiceInter;

public class RoleServiceImpl implements RoleServiceInter{

	private RoleDaoInter roleDao;//声明一个存dao层接口的属性
	
	//在添加人员时查角色
	@Override
	public List<Role> queryAllRole() {
		List<Role> list=roleDao.queryAllRole();
		return list;
	}
	//在角色管理添加角色
	@Override
	public List<Role> queryRoles(Role role) {
		List<Role> list=roleDao.queryRoles(role);
		return list;
	}
	
	//插入角色
	@Override
	public void insertRole(Role role) {
		roleDao.insertRole(role);	
	}
		
	//得到最大的角色id,用于插入中间表
	@Override
	public int getMaxId() {
		
		return roleDao.getMaxId();
	}
	
	//插入角色-菜单中间表
	@Override
	public void insertRole_menu(Role_menu rm) {
		roleDao.insertRole_menu(rm);		
	}
	
	//根据Id查询
	@Override
	public Role queryRoleById(int id) {
		Role role=roleDao.queryRoleById(id);
		return role;
	}
	
	//更新角色
	@Override
	public void updateRole(Role role) {
		roleDao.updateRole(role);		
	}
	
	//根据id删除
	@Override
	public void delelteRoleById(int rid) {
		roleDao.delelteRoleById(rid);		
	}
	
	//删除中间表数据
	@Override
	public void deleteRole_menu(int rmid) {
		roleDao.deleteRole_menu(rmid);
		
	}
	//统计总的数据条数
	@Override
	public int totalSize(Role menu) {
		int nums=roleDao.totalSize(menu);
		return nums;
	}
			
	
	public RoleDaoInter getRoleDao() {
		return roleDao;
	}
	public void setRoleDao(RoleDaoInter roleDao) {
		this.roleDao = roleDao;
	}
	
		
}
