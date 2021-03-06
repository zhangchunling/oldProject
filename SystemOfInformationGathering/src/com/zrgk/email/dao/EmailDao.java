package com.zrgk.email.dao;

import java.util.List;

import com.zrgk.email.model.Email;
import com.zrgk.email.model.Receive;
import com.zrgk.permission.model.Employee;
import com.zrgk.utils.Page;

public interface EmailDao {

//	保存发送信息的方法
	void save(Email e);

//	得到人员表中人员的方法
	List<Employee> queryAllEmp();

//	通过收件人得到它的主键id的值
	Email getEmailId(Email email);

//	保存收件人的方法
	void addReceive(Receive r);

//	通过id删除的方法；
 
	void deleteById(Integer id);

//	通过哦id得到这一条所有的信息的方法
	Email queryById(Integer id);

//	得到发件箱的总的发件条数
	
	int totalSize(String send);

//	得到收件箱的相关信息的方法
	List getGiveemail( Page p,Email e);

//	到email表中name=send;的所有信息的方法
	List<Email> ExportQueryAll(String send);

//	得到收件箱中收件箱的所有信息
	List<Email> QueryBySend(Email e,Page p);

//	得到收件箱的总条数的方法
	int totalSize2(String send);

//	修改状态的方法
	void upState(Email e);

//	通过ids删除收件箱中的信息方法S
	void deleteByIdReceive(int id);

	 

	
	
	
}
