package com.zrgk.permission.model;

import java.io.Serializable;

/**
 * 人员角色中间表的javabean
 * @author Administrator
 *	2015-06-09
 */
public class Emp_role implements Serializable{
	private static final long serialVersionUID = -7798231490210777312L;
	
	private Integer rid;//角色id
	private Integer eid;//人员id
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}

	
}
