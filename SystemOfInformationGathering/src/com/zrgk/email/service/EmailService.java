package com.zrgk.email.service;

import java.util.List;

import com.zrgk.email.model.Email;
import com.zrgk.email.model.Receive;
import com.zrgk.permission.model.Employee;
import com.zrgk.utils.Page;

public interface EmailService {

	void save(Email e);

	List<Employee> queryAllEmp();

	Email getEmailId(Email email);

	void addReceive( Receive r);

	List getGiveemail( Page p,Email e);

	void deleteById(Integer id);

	Email queryById(Integer id);

	int totalSize(String send);

	List<Email> ExportQueryAll(String send);

	List<Email> QueryBySend(Email e,Page p);

	int totalSize2(String send);

	void upState(Email e);

	void deleteByidReceive(int id);

	 
 
	 
 
}
