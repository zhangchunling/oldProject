package com.zrgk.contact.service;

import java.util.List;

import com.zrgk.contact.model.My_Contact;
import com.zrgk.utils.Page;

public interface ContactServicInter {
	//新增个人通讯录
	void insertContact(My_Contact contact);
	//查询用户信息
	List<My_Contact> queryContact(My_Contact contact,Page pa);
	//按cid查询
	public My_Contact queryContactById(int cid);
	//删除通讯录方法
	void deleteContact(My_Contact contact);
	//修改通讯录信息
	void updateContact(My_Contact contact);

	//总条数（分页用到的）
	int totalSize(My_Contact contact);
	
}
