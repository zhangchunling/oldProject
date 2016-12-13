package com.zrgk.permission.model;

import java.io.Serializable;

import com.zrgk.utils.Page;

public class Role extends Page implements Serializable{

	private static final long serialVersionUID = -5550756291313658423L;
	
	private int rid;		//主键	
	private String roleName;//角色名称
	private Integer r_state;//状态
	private String strRole; //菜单id拼接字符串
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getR_state() {
		return r_state;
	}
	public void setR_state(Integer r_state) {
		this.r_state = r_state;
	}
	public String getStrRole() {
		return strRole;
	}
	public void setStrRole(String strRole) {
		this.strRole = strRole;
	}
	
}
