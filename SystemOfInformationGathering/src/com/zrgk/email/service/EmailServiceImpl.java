package com.zrgk.email.service;

import java.util.List;

import com.zrgk.email.dao.EmailDao;
import com.zrgk.email.model.Email;
import com.zrgk.email.model.Receive;
import com.zrgk.permission.model.Employee;
import com.zrgk.utils.Page;

public class EmailServiceImpl implements EmailService {

	private EmailDao emailDao;
	
	public int totalSize(String send){
		
		return emailDao.totalSize(send);
		 
	 	
	}
	

	public EmailDao getEmailDao() {
		return emailDao;
	}

	public void setEmailDao(EmailDao emailDao) {
		this.emailDao = emailDao;
	}

	@Override
	public void save(Email e) {
		 emailDao.save(e);
		
	}

	@Override
	public List<Employee> queryAllEmp() {
		 
		return emailDao.queryAllEmp();
	}

	@Override
	public Email getEmailId(Email email) {
		 
		return  emailDao.getEmailId(email);
	}

	@Override
	public void addReceive(Receive r) {
		 
		emailDao.addReceive(r);
	}

	@Override
	public List getGiveemail( Page p,Email e) {
		 
		return   emailDao.getGiveemail(p,e);
 }

	@Override
	public void deleteById(Integer id) {
	 
		emailDao.deleteById(id);
	}

	@Override
	public Email queryById(Integer id) {
		 
		return emailDao.queryById(id);
	}


	@Override
	public List<Email> ExportQueryAll(String send) {
		 
		return emailDao.ExportQueryAll(send);
	}


	@Override
	public List<Email> QueryBySend(Email e ,Page p) {
		 
		return emailDao.QueryBySend(e,p);
	}


	@Override
	public int totalSize2(String send) {
		 
		return  emailDao.totalSize2(send);
	}


	@Override
	public void upState(Email e) {
		 emailDao.upState(e);
	}


	@Override
	public void deleteByidReceive(int id) {
		 
		emailDao.deleteByIdReceive(id);
		
	}


	 
 
	
//	===============方法的位�?===========================
	
}
