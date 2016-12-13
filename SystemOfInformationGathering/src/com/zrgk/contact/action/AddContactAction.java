package com.zrgk.contact.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zrgk.contact.model.ExcekMy_Contaca;
import com.zrgk.contact.model.My_Contact;
import com.zrgk.contact.service.ContactServicInter;
import com.zrgk.email.model.Email;
import com.zrgk.permission.service.EmployeeServiceInter;
import com.zrgk.utils.ExportExcel;
import com.zrgk.utils.Page;

/**
 * 新增通讯录
 * @author samson
 * 2015-07-20
 */
//继承ActionSupport类 将一个普通类变成Struts2类
public class AddContactAction extends ActionSupport{

	//依赖注入：声明一个存service层接口的属性
	private ContactServicInter contactService;
	//声明跳转页面的路径
	private String resultpath;
	//声明javabean
	private My_Contact myContact;
	//声明一个集合
	private List<My_Contact> list;
	//上一页
	private Integer upPage;
	//下一页
	private Integer nextPage;
	//尾页
	private Integer endPage;
	//传过来的当前页
	private Integer current;
	//总条数
	private Integer total;
	//声明一个分页list
	private List list1 = new ArrayList<>();
	
	private Integer cid;
	//存选中删除传来的拼接字符串
	private String employeeStrValue;
	
	//批量删除
	private String boxId;
	private String[] str;
	
	//模糊查询
	private String cname;
	private String cphone;

	//查询
	public String queryContact(){
		if (current == null) {
			current = 1;
		}
		
		if(myContact == null){
			myContact = new My_Contact();
		}
		
		myContact.setCname(cname);
		myContact.setCphone(cphone);
		
		Page pa =new Page();
		//得到个人通讯录总的条数
		total = contactService.totalSize(myContact);
		pa.setTotalSize(total);
		pa.setCurrentPage(current);
		// 分页的要用到的属性只，其中，nextPage是下一页的值，uppage是上一页的值，endpage最有一页的值
		if (current == pa.getTotalPage()) {
			nextPage = current;
			System.out.println("current===" + current);

		} else {
			nextPage = current + 1;
		}
		if (current > 1) {
			upPage = current - 1;
		} else {
			upPage = current;
		}

		endPage = pa.getTotalPage();
		
		// 数字分页用到的页数值，用list集合传到页面上去
				if (endPage > 0) {
					list1.clear();

					for (int i = 1; i <= pa.getTotalPage(); i++) {
					list1.add(i);
					}

				}

		list = contactService.queryContact(getMyContact(),pa);
		this.setResultpath("/AddressList/index.jsp");
		return "gotojsp";
	}
	
	//按ID查
	public String queryContactById(){
		myContact = contactService.queryContactById(cid);
		this.setResultpath("/AddressList/edit.jsp");
		return "gotojsp";		
	}
	//添加
	public String insertContact(){
		contactService.insertContact(myContact);
		
		this.setResultpath("AddContact_queryContact");
		return "gotoaction";
	}
	//修改
	public String updateContact(){
		
		contactService.updateContact(myContact);
		
		this.setResultpath("AddContact_queryContact");		
		return "gotoaction";
	}
	//删除
	public String deleteContact(){
		myContact =contactService.queryContactById(cid);
		contactService.deleteContact(myContact);
		this.setResultpath("AddContact_queryContact");
		
		return "gotoaction";
	}
	//批量删除
	public String delAll(){
		str =boxId.split(",");
		for (int i = 0; i < str.length; i++) {
			cid = Integer.parseInt(str[i]);
			myContact = contactService.queryContactById(cid);
			contactService.deleteContact(myContact);
		}
		this.setResultpath("AddContact_queryContact");
		return "gotoaction";
	}
	
//	导出export方法
	public String exporContact() throws IOException{

		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType("text/html;charset=utf-8");
        response.setContentType("octets/stream");
		
        response.addHeader("Content-Disposition",
				"attachment;filename=email.xls");

      //创建导出Excel对象，来源工具包
		ExportExcel<ExcekMy_Contaca> ex = new ExportExcel<ExcekMy_Contaca>();
		//声明一个数组  里面数据是设置导出的excel头部分（列名）
		String[] headers = {  "姓名", "性别", "日期", "电话号", "地址", "邮箱"
				 };

		List<ExcekMy_Contaca> listEmpExcel = new ArrayList<ExcekMy_Contaca>();
		if(employeeStrValue!=null&&!"".equals(employeeStrValue.trim())){
			String[] strArray=employeeStrValue.split(",");
			for (int i = 0; i < strArray.length; i++) {
				My_Contact m=contactService.queryContactById((Integer.parseInt(strArray[i].trim())));
				ExcekMy_Contaca exm=new ExcekMy_Contaca();
				exm.setCname(m.getCname());
				if (m.getCsex()==1) {
					exm.setCsex("男");
				}
				if (m.getCsex()==2) {
					exm.setCsex("女");
				}
				exm.setCdate(m.getCdate());
				exm.setCphone(m.getCphone());
				exm.setCaddress(m.getCaddress());
				exm.setCemail(m.getCemail());
				
				listEmpExcel.add(exm);
			}
		}
		//创建输出流
		OutputStream outputStream = response.getOutputStream();
		ex.exportExcel(headers, listEmpExcel, outputStream);
		outputStream.close();
		
		return null;

	}

	
	
	public List getList1() {
		return list1;
	}

	public void setList1(List list1) {
		this.list1 = list1;
	}

	public Integer getUpPage() {
		return upPage;
	}

	public void setUpPage(Integer upPage) {
		this.upPage = upPage;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getEndPage() {
		return endPage;
	}

	public void setEndPage(Integer endPage) {
		this.endPage = endPage;
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getCphone() {
		return cphone;
	}
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	public String getBoxId() {
		return boxId;
	}
	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}
	public String[] getStr() {
		return str;
	}
	public void setStr(String[] str) {
		this.str = str;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public ContactServicInter getContactService() {
		return contactService;
	}
	public void setContactService(ContactServicInter contactService) {
		this.contactService = contactService;
	}
	public String getResultpath() {
		return resultpath;
	}
	public void setResultpath(String resultpath) {
		this.resultpath = resultpath;
	}
	public My_Contact getMyContact() {
		return myContact;
	}
	public void setMyContact(My_Contact myContact) {
		this.myContact = myContact;
	}
	public List<My_Contact> getList() {
		return list;
	}
	public void setList(List<My_Contact> list) {
		this.list = list;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getEmployeeStrValue() {
		return employeeStrValue;
	}

	public void setEmployeeStrValue(String employeeStrValue) {
		this.employeeStrValue = employeeStrValue;
	}
	

}
