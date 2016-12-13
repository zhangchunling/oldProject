package com.zrgk.manu.bean;

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

public class PageFile extends Page implements Serializable {

	private static final long serialVersionUID = 5039022199537990837L;
/*	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="pf_seq")   
	@SequenceGenerator(name="pf_seq", sequenceName="seq_id")  */
	private Integer pf_id;
	private String pf_author;
	private String pf_name;
	private String pf_type;
	private String pf_style;
	private Clob pf_content;
	private Integer pf_mu_id;
	private Integer pf_u_id;
	private String pf_createtime;
	private Integer pf_state=0;//0 未提交 1 未审核 2审核通过、3审核未通过、4未录用 5已录用
	private String content;//转存clob
	private String subContent;
	
	
	
	public PageFile(Integer pf_id){//设置一个有参构造
		this.pf_id=pf_id;
	}
	public PageFile(){
		
	}
	
	
	public Integer getPf_id() {
		return pf_id;
	}
	public void setPf_id(Integer pf_id) {
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

	public Integer getPf_mu_id() {
		return pf_mu_id;
	}
	public Clob getPf_content() {
		return pf_content;
	}
	public void setPf_content(Clob pf_content) {
		//这里clob不能为空 否则转换异常 
		if(pf_content!=null){
		//System.out.println("装配稿件"+ClobToString.ClobToString((pf_content)));
			content=ClobToString.ClobToString(pf_content);
			this.setSubContent(content.trim().substring(0,10)+"···");//转存内容前十，用于列表展示
		}
		this.pf_content = pf_content;
	}
	public void setPf_mu_id(Integer pf_mu_id) {
		this.pf_mu_id = pf_mu_id;
	}
	public Integer getPf_u_id() {
		return pf_u_id;
	}
	public void setPf_u_id(Integer pf_u_id) {
		this.pf_u_id = pf_u_id;
	}
	public String getPf_createtime() {
		return pf_createtime;
	}
	public void setPf_createtime(String pf_createtime) {
		this.pf_createtime = pf_createtime;
	}
	
	
	public Integer getPf_state() {
		return pf_state;
	}
	public void setPf_state(Integer pf_state) {
		this.pf_state = pf_state;
	}
	//转存CLOB
	public String getContent() {
		return this.content;
	}
	
	
	public String getSubContent() {
		return subContent;
	}
	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}
	public void setContent(String content) throws SerialException, SQLException {
//		this.pf_content=new SerialClob(content.toCharArray());
		System.out.println("set content----"+content);
		this.pf_content=Hibernate.createClob(content);
		//SerializableClob proxy = Proxy.getInvocationHandler(new SerialClob(content.toCharArray())); 
		//java.sql.Clob realClob = proxy.getWrappedClob();
		//this.pf_content=new SerialClob(content.toCharArray());
		//this.pf_content=realClob;
		this.content = content;
	}
	@Override
	public String toString() {
		return "PageFile [pf_id=" + pf_id + ", pf_author=" + pf_author
				+ ", pf_name=" + pf_name + ", pf_type=" + pf_type
				+ ", pf_style=" + pf_style + ", pf_content=" + pf_content
				+ ", pf_mu_id=" + pf_mu_id + ", pf_u_id=" + pf_u_id
				+ ", pf_createtime=" + pf_createtime + ", pf_state=" + pf_state
				+ ", content=" + content+  "]";
	}

	

	
	

}
