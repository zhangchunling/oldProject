package com.zrgk.contact.model;

import java.io.Serializable;

import com.zrgk.utils.Page;

public class ExcekMy_Contaca extends Page implements Serializable{
	private String cname;//名字
	private String csex;//性别
	private String cdate;//日期
	private String cphone;//电话号
	private String caddress;//地址
	private String cemail;//邮箱
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCsex() {
		return csex;
	}
	public void setCsex(String csex) {
		this.csex = csex;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	public String getCphone() {
		return cphone;
	}
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	public String getCaddress() {
		return caddress;
	}
	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}
	public String getCemail() {
		return cemail;
	}
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	
	
	
}
