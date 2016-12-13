package com.zrgk.self.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zrgk.contact.model.ExcekMy_Contaca;
import com.zrgk.contact.model.My_Contact;
import com.zrgk.permission.model.Employee;
import com.zrgk.self.model.ExportEmployee1;
import com.zrgk.self.service.EmployeeServiceInter;
import com.zrgk.utils.ExportExcel;
import com.zrgk.utils.MD5;
import com.zrgk.utils.Page;

public class GerenxinxiAction extends ActionSupport{
	
	private EmployeeServiceInter selfService;
	
	private String resultpath;
	
	private Employee employee;
	
	private List<Employee> list;
	
	private String oldPassword;
	//存选中删除传来的拼接字符串
	private String employeeStrValue;
	private String confirmP;
	
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
	//模糊查询
	private String cname;
	private String cphone;
	
	//查询1
	public String querySelfByid(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		int eid=(int) session.getAttribute("userId");		
		employee = selfService.queryEmpById(eid);
		resultpath="/Self/selfinfo.jsp";//【千万别加斜杠：/】
		return "gotojsp";
		
	}
	
	public String updatePassword(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		int eid=(int) session.getAttribute("userId");
		Employee emp = selfService.queryEmpById(eid);
		String newPassord=MD5.createPassword(employee.getPassword());
		emp.setPassword(newPassord);
		selfService.updateEmployee(emp);
		resultpath="/Self/password.jsp";
		return "gotojsp";
	}
	
	public String checkPasswordAjax() throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();//out打印的字符串可在js里用data得到
		HttpSession session=ServletActionContext.getRequest().getSession();
		int eid=(int) session.getAttribute("userId");
		employee = selfService.queryEmpById(eid);
		String jiami=MD5.createPassword(oldPassword);
		if(employee.getPassword().equals(jiami)){
			out.print("1");
		}
		 out.close();
		return null;
	}

	
	//1.查询所有员工
	public String queryEmployee(){
		if (current == null) {
			current = 1;
		}
		
		if(employee == null){
			employee = new Employee();
		}
		employee.setEmpName(cname);
		employee.setTel(cphone);
		Page pa =new Page();
		//得到个人通讯录总的条数
		total = selfService.totalSize(employee);
		employee.setTotalSize(total);
		employee.setCurrentPage(current);
		// 分页的要用到的属性只，其中，nextPage是下一页的值，uppage是上一页的值，endpage最有一页的值
		if (current == employee.getTotalPage()) {
			nextPage = current;
			//System.out.println("current===" + current);

		} else {
			nextPage = current + 1;
		}
		if (current > 1) {
			upPage = current - 1;
		} else {
			upPage = current;
		}

		endPage = employee.getTotalPage();
		
		// 数字分页用到的页数值，用list集合传到页面上去
		if (endPage > 0) {
			list1.clear();

			if (endPage<=5) {
				
				for (int i = 1; i <= employee.getTotalPage(); i++) {
					list1.add(i);
				}
			}else {
				for (int i = 1; i<=5; i++) {
					list1.add(i);
				}
			}

		}
		//根据employee查回总的数条数
		int nums=selfService.totalSize(employee);
		
		//分页
		if(employee!=null){
			
			//将总的条数据塞进page对象时，就自动算出了总页数。Employee对象继承了Page对象
			//employee.setTotalSize(nums);//【【其实可以直接用查回的人员list.size()得到总条数，跟后一句代码换个位置】】			
			//查询所有员工
			list=selfService.queryEmployee(employee);				
			
		}		
		
		//跳转路径
		resultpath="/AddressList/queryEmployee.jsp";
		return "gotojsp";
	}
	
//	导出export方法
	public String exporContact() throws IOException{

		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType("text/html;charset=utf-8");
        response.setContentType("octets/stream");
		
        response.addHeader("Content-Disposition",
				"attachment;filename=email.xls");

      //创建导出Excel对象，来源工具包
        ExportExcel<ExportEmployee1> ex = new ExportExcel<ExportEmployee1>();
		//ExportExcel<ExcekMy_Contaca> ex = new ExportExcel<ExcekMy_Contaca>();
		//声明一个数组  里面数据是设置导出的excel头部分（列名）
		String[] headers = {  "姓名", "性别", "日期", "地址", "邮箱", "电话号"
				 };

		List<ExportEmployee1> listEmpExcel = new ArrayList<ExportEmployee1>();
		if(employeeStrValue!=null&&!"".equals(employeeStrValue.trim())){
			String[] strArray=employeeStrValue.split(",");
			for (int i = 0; i < strArray.length; i++) {
				Employee m=selfService.queryEmpById((Integer.parseInt(strArray[i].trim())));
				ExportEmployee1 exm=new ExportEmployee1();
				exm.setEmpName(m.getEmpName());
				if (m.getSex()==1) {
					exm.setSex("男");
				}
				if (m.getSex()==0) {
					exm.setSex("女");
				}
				exm.setBirthday(m.getBirthday());
				exm.setTel(m.getTel());
				exm.setAddress(m.getAddress());
				exm.setEmail(m.getEmail());
				
				listEmpExcel.add(exm);
			}
		}
		//创建输出流
		OutputStream outputStream = response.getOutputStream();
		ex.exportExcel(headers, listEmpExcel, outputStream);
		outputStream.close();
		
		return null;

	}
	
	public EmployeeServiceInter getSelfService() {
		return selfService;
	}

	public void setSelfService(EmployeeServiceInter selfService) {
		this.selfService = selfService;
	}

	public String getResultpath() {
		return resultpath;
	}

	public void setResultpath(String resultpath) {
		this.resultpath = resultpath;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getList() {
		return list;
	}

	public void setList(List<Employee> list) {
		this.list = list;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
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

	public List getList1() {
		return list1;
	}

	public void setList1(List list1) {
		this.list1 = list1;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCphone() {
		return cphone;
	}

	public void setCphone(String cphone) {
		this.cphone = cphone;
	}

	public String getEmployeeStrValue() {
		return employeeStrValue;
	}

	public void setEmployeeStrValue(String employeeStrValue) {
		this.employeeStrValue = employeeStrValue;
	}

	public String getConfirmP() {
		return confirmP;
	}

	public void setConfirmP(String confirmP) {
		this.confirmP = confirmP;
	}


}
