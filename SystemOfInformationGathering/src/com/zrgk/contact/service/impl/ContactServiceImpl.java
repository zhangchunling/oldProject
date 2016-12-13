package com.zrgk.contact.service.impl;


import java.util.List;

import com.zrgk.contact.dao.ContactDaoInter;
import com.zrgk.contact.model.My_Contact;
import com.zrgk.contact.service.ContactServicInter;
import com.zrgk.utils.Page;

public class ContactServiceImpl implements ContactServicInter{
	//set注入：声明一个存dao层接口属性，然后生成cutest方法
	private ContactDaoInter contactDao;	
	
	//set注入生成的get、set
	public ContactDaoInter getContactDao() {
		return contactDao;
	}

	public void setContactDao(ContactDaoInter contactDao) {
		this.contactDao = contactDao;
	}
	//
	@Override
	public void insertContact(My_Contact contact) {
		contactDao.insertContact(contact);
		
	}
	//模糊查询
	@Override
	public List<My_Contact> queryContact(My_Contact contact,Page pa) {
		
		return contactDao.queryContact(contact,pa);
	}
	//根据CID查询
	@Override
	public My_Contact queryContactById(int cid) {		
		return contactDao.queryContactById(cid);
	}
	//删除信息
	@Override
	public void deleteContact(My_Contact contact) {
		contactDao.deleteContact(contact);
		
	}
	//添加信息
	@Override
	public void updateContact(My_Contact contact) {
		contactDao.updateContact(contact);
		
	}
	//分页
	@Override
	public int totalSize(My_Contact contact) {
		return contactDao.totalSize(contact);
	}

	
	
	
}
