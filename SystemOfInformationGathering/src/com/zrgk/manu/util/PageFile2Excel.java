package com.zrgk.manu.util;

import java.io.Serializable;
import java.lang.reflect.Proxy;
import java.sql.Clob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.Hibernate;
import org.hibernate.lob.SerializableClob;
import org.springframework.orm.hibernate3.support.ClobStringType;
import org.springframework.orm.ibatis.support.ClobStringTypeHandler;

import com.zrgk.manu.util.ClobToString;
import com.zrgk.manu.util.Page;
/**
 * PageFile 表对应的BEAN
 * @author Bleach
 *
 */

public class PageFile2Excel extends Page implements Serializable {

	private int pf_id;
	private String pf_author;
	private String pf_name;
	private String pf_type;
	private String pf_style;
	private String pf_createtime;
	private String pf_state;
	private String subContent;
	
	
	
	public String getPf_state() {
		return pf_state;
	}
	public void setPf_state(String pf_state) {
		this.pf_state = pf_state;
	}
	public int getPf_id() {
		return pf_id;
	}
	public void setPf_id(int pf_id) {
		this.pf_id = pf_id;
	}
	public String getPf_author() {
		return pf_author;
	}
	public void setPf_author(String pf_author) {
		this.pf_author = pf_author;
	}
	public String getPf_name() {
		return pf_name;
	}
	public void setPf_name(String pf_name) {
		this.pf_name = pf_name;
	}
	public String getPf_type() {
		return pf_type;
	}
	public void setPf_type(String pf_type) {
		this.pf_type = pf_type;
	}
	public String getPf_style() {
		return pf_style;
	}
	public void setPf_style(String pf_style) {
		this.pf_style = pf_style;
	}
	public String getPf_createtime() {
		return pf_createtime;
	}
	public void setPf_createtime(String pf_createtime) {
		this.pf_createtime = pf_createtime;
	}
	
	public String getSubContent() {
		return subContent;
	}
	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}

	
	

}
