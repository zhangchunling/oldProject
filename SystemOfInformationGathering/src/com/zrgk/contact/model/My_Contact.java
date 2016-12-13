package com.zrgk.contact.model;

import java.io.Serializable;

import com.zrgk.utils.Page;

/**
 * mycontact表对应的javabean
 * @author samson
 * 2015-07-20
 */

public class My_Contact extends Page implements Serializable{
	
	private Integer cid;
	private String cname;//名字
	private int csex;//性别
	private String cdate;//日期
	private String cphone;//电话号
	private String caddress;//地址
	private String cemail;//邮箱
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getCsex() {
		return csex;
	}
	public void setCsex(int csex) {
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
	@Override
	public String toString() {
		return "My_Contact [cid=" + cid + ", cname=" + cname + ", csex=" + csex
				+ ", cdate=" + cdate + ", cphone=" + cphone + ", caddress="
				+ caddress + ", cemail=" + cemail + "]";
	}
	
	
	
	
	
	
}
