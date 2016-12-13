package com.zrgk.email.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zrgk.email.model.Email;
import com.zrgk.email.model.Receive;
import com.zrgk.email.service.EmailService;
import com.zrgk.utils.ExportExcel;
import com.zrgk.utils.Page;

/**
 * 
 * 邮件系统的action
 * 
 * @author wfh
 * 
 *         2015-7-29
 * 
 */

public class EmailAction extends ActionSupport {

	// ========================生成属性的位置=====================================

	private static final long serialVersionUID = -3454284959875115674L;

	private EmailService emailService;

	private Integer nextPage;

	private String fileName;
	private Integer upPage;

	private Integer title;
	private Integer endPage;

	private Integer current;

	private Integer current2;

	private String resultpath;

	private Integer id;

	private Integer total;

	private Email email;

	private String keyword;

	private Integer order;
	private String ids;
	private List<File> emailFile;

	private String emailFileContentType;

	private List<String> emailFileFileName;

	private List emp;
	private List<Email> giveemail;

	private List<Email> reList;
	private Email looks;
	private Email receives;
	private String[] accessorys;

	private List list = new ArrayList<>();

	// ======================写方法的位置========================================

	// ================发件箱=====================

	// 收件箱的主頁面的geiveemail方法;
	public String giveemail() {

		if (current == null) {
			current = 1;
		}

		HttpSession session = ServletActionContext.getRequest().getSession();

		String send = String.valueOf(session.getAttribute("userInfo"));
		System.out.println(send+"==========");
		Email e = new Email();

		// 頁面上得到要排序的值
		if (order != null && order == 1) {
			e.setSendTime("order");
			session.setAttribute("order", order);
		}
		// 页面上得到的模糊查询的属性值
		if (title != null && title == 1) {

			if (keyword != null && !"".equals(keyword)) {
				session.setAttribute("key", keyword);

				e.setTitle(keyword);
				session.setAttribute("title", title);

			}
		}

		Page pa = new Page();

		// 得到收件箱中发送的总页数
		total = emailService.totalSize(send);
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
			list.clear();

			for (int i = 1; i <= pa.getTotalPage(); i++) {

				list.add(i);
			}

		}

		// e.setSent(send)这是把发件人放到bean中，为了手收件人时服务的，虽然这里用不着；
		e.setSend(send);
		// 得到我所有的收件信息的方法，然后传到页面上去
		 List<Email> li = new ArrayList<Email>();
		 giveemail  = emailService.getGiveemail(pa, e);
		 
		for (int i = 0; i < giveemail.size(); i++) {
			
			Email em=giveemail.get(i);
			if (em.getContent().length()>15) {
				em.setContent(em.getContent().substring(0, 15));
				li.add(em);
			}else {
				li.add(em);
			}
		}
		giveemail=li;
		
		this.setResultpath("/Email/giveemail.jsp");
		return "gotojsp";

	}

	// 发件箱的清空方法
	public String empty() {
		HttpSession session = ServletActionContext.getRequest().getSession();

		order = 0;
		keyword = null;
		title = 0;
		session.removeAttribute("key");
		session.removeAttribute("title");
		session.removeAttribute("order");

		System.out.println("清空之后的keyword==" + keyword);

		this.setResultpath("/Email/giveemail.jsp");
		return "gotojsp";

	}

	// 收件箱的清空方法
	public String empty2() {
		HttpSession session = ServletActionContext.getRequest().getSession();

		session.removeAttribute("key");
		session.removeAttribute("title");
		session.removeAttribute("order");
		order = 0;
		keyword = null;
		title = 0;

		this.setResultpath("/Email/receiveemail.jsp");
		return "gotojsp";

	}

	public String queryById() {

		System.out.println("queryById中的页面传过来的值id==" + id);

		looks = emailService.queryById(id);

		id = null;
		System.out.println(looks.getAccessory() + looks.getReceive());
		this.setResultpath("/Email/look.jsp");
		return "gotojsp";
	}

	// 发件箱的删除方法
	public String deleteById() {

		System.out.println("action传到的id值===" + id);
		emailService.deleteById(id);
		emailService.deleteByidReceive(id);
		id = null;

		this.setResultpath("email_giveemail");
		return "gotoaction";
	}

	// 收件箱的删除方法
	public String deleteById2() {

		System.out.println("action传到的id值===" + id);
		emailService.deleteById(id);
		emailService.deleteByidReceive(id);
		id = null;
		this.setResultpath("email_receiveemail");
		System.out.println("deleteById2============");
		return "gotoaction";
	}

	// 发件箱的选择删除的方法
	public String selectDelete() {

		System.out.println("得到的ids=" + ids);
		String[] deletes = ids.split(",");
		if (deletes.length > 0) {

			for (int i = 0; i < deletes.length; i++) {

				emailService.deleteById(Integer.parseInt(deletes[i].trim()));
				emailService.deleteByidReceive(Integer.parseInt(deletes[i]
						.trim()));
			}
		}

		ids = null;
		this.setResultpath("email_giveemail");
		return "gotoaction";
	}

	// 收件箱的选择删除方法
	public String selectDelete2() {

		System.out.println("得到的ids=" + ids);
		String[] deletes = ids.split(",");
		if (deletes.length > 0) {

			for (int i = 0; i < deletes.length; i++) {

				emailService.deleteById(Integer.parseInt(deletes[i].trim()));
				emailService.deleteByidReceive(Integer.parseInt(deletes[i]
						.trim()));
			}
		}

		ids = null;
		this.setResultpath("email_receiveemail");
		return "gotoaction";
	}

	// ==========================发邮件=======================

	// 发送邮件的方法，也就是把发送的信息增加到数库的方法
	public String sendEmail() throws Exception {

		this.setResultpath("/Email/suc.jsp");

		HttpSession session = ServletActionContext.getRequest().getSession();

		String send = String.valueOf(session.getAttribute("userInfo"));
		// 也一个当前的时间
		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		String sendTime = sdf.format(date);

		String accessory = "";
		String newAccessory="";
		if (emailFile!=null&&emailFile.size() > 0) {

			// 循环上传文件的方法，这样可以实现动态上传多个文件
			for (int i = 0; i < emailFile.size(); i++) {

				String newPath = ServletActionContext.getServletContext()
						.getRealPath("upload/" + emailFileFileName.get(i));
				FileInputStream is = new FileInputStream(emailFile.get(i));
				FileOutputStream os = new FileOutputStream(new File(newPath));

				IOUtils.copy(is, os);
				os.flush();
				os.close();
				is.close();

				accessory = accessory + emailFileFileName.get(i) + ",";
			}
			  newAccessory = accessory.substring(0, accessory.length() - 1);
		}
		email.setAccessory(newAccessory);
		email.setSendTime(sendTime);
		email.setSend(send);
		// 给一个默认状态，其中1代表是未读
		email.setState(1);

		// 执行保存的方法
		emailService.save(email);
		// 因为emailFileName是全局变量，所以用完之后要清楚；
		
		 if (emailFileFileName!=null&&emailFileFileName.size()>0) {
			
			 emailFileFileName.clear();
		}

		Receive r = new Receive();

		Email e = new Email();

		e.setSendTime(sendTime);

		// 循环增加收件人，因为收件人不止一个，所以要循环增加到receive表中去
		int eid = 0;
		if (emailService.getEmailId(e) != null) {
			eid = emailService.getEmailId(e).getId();
		}

		String[] receives = email.getReceive().split(",");
		//System.out.println("receive====" + email.getReceive());
		for (int i = 0; i < receives.length; i++) {

			r.setRid(eid);
			r.setRname(receives[i].trim());
			// 增加收件人的方法
			emailService.addReceive(r);
		}

		return "gotojsp";

	}

	// 得到人员表里所有的人员姓名的方法
	public String receives() {

		// 得到人员表中所有的人员方法
		emp = emailService.queryAllEmp();

		this.setResultpath("/Email/sendemail.jsp");
		return "gotojsp";
	}

	// 到处发件箱的export的方法
	public void outExport() throws IOException {

		HttpServletResponse response = ServletActionContext.getResponse();

		response.addHeader("Content-Disposition",
				"attachment;filename=email.xls");

		HttpSession session = ServletActionContext.getRequest().getSession();

		ExportExcel<Email> ex = new ExportExcel<Email>();

		String[] headers = { "邮件编号", "发件人", "收件人", "标题", "状态", "内容", "附件名",
				"发送时间" };

		List<Email> dataSet = new ArrayList<Email>();

		String send = String.valueOf(session.getAttribute("userInfo"));
		dataSet = emailService.ExportQueryAll(send);

		OutputStream out = response.getOutputStream();
		ex.exportExcel(headers, dataSet, out);
		out.close();
		System.out.println("excel导出成功！");

	}

	// 收件箱的到处export的方法
	public void outExport2() throws IOException {

		HttpServletResponse response = ServletActionContext.getResponse();

		response.addHeader("Content-Disposition",
				"attachment;filename=email.xls");

		HttpSession session = ServletActionContext.getRequest().getSession();

		ExportExcel<Email> ex = new ExportExcel<Email>();

		String[] headers = { "邮件编号", "发件人", "收件人", "标题", "内容", "附件名", "发送时间" };

		List<Email> dataSet = new ArrayList<Email>();

		String send = String.valueOf(session.getAttribute("userInfo"));
		Email e = new Email();

		e.setSend(send);
		System.out.println("send====" + send);
		Page p = new Page();
		dataSet = emailService.QueryBySend(e, p);

		System.out.println("得到的数据是+====" + dataSet);

		OutputStream out = response.getOutputStream();
		ex.exportExcel(headers, dataSet, out);
		out.close();
		System.out.println("excel22222导出成功！");

	}

	// =======================收件箱=================================

	// 收件箱中通过id查询所有信息，然后转到详情页面中，也就是所谓的回写方法
	public String queryByIdReceives() {

		System.out.println("queryById中的页面传过来的值id==" + id);

		receives = emailService.queryById(id);

		// 把得到总得附件名用","分割开，然后放到一个数组中，为下载附件服务的

		accessorys = receives.getAccessory().split(",");

		id = null;
		this.setResultpath("/Email/lookReceive.jsp");
		return "gotojsp";
	}

	// 修改状态的方法
	public String state() {

		Email e = new Email();

		System.out.println("进到state方法中。。。。。。。");
		e.setId(id);
		e.setState(2);
		System.out.println("id ===" + id);
		emailService.upState(e);

		this.setResultpath("/Email/lookReceive.jsp");
		return "gotojsp";
	}

	// 收件箱的主页面的回写值的方法
	public String receiveemail() {

		if (current2 == null) {
			current2 = 1;
		}
		HttpSession session = ServletActionContext.getRequest().getSession();

		// 得到用户名的方法
		String send = (String) session.getAttribute("userInfo");

		 System.out.println("shou========"+send);
		Email e = new Email();

		e.setSend(send);

		if (order != null && order == 1) {
			e.setSendTime("order");
			session.setAttribute("order", order);
		}
		if (title != null && title == 1) {

			if (keyword != null && !"".equals(keyword)) {
				session.setAttribute("key", keyword);
				e.setTitle(keyword);
				session.setAttribute("title", title);

			}
		}

		Page pa = new Page();

		total = emailService.totalSize2(send);

		pa.setCurrentPage(current2);
		pa.setTotalSize(total);

		if (current2 == pa.getTotalPage()) {
			nextPage = current2;
		} else {
			nextPage = current2 + 1;
		}
		if (current2 > 1) {
			upPage = current2 - 1;
		} else {
			upPage = current2;
		}

		endPage = pa.getTotalPage();

		if (endPage > 0) {
			list.clear();
			for (int i = 1; i <= pa.getTotalPage(); i++) {
				list.add(i);
			}

		}
		System.out.println("total=" + total);
		reList = emailService.QueryBySend(e, pa);
		
		 List<Email> lis= new ArrayList<Email>();
		 
			for (int i = 0; i < reList.size(); i++) {
				
				Email em=reList.get(i);
				if (em.getContent().length()>15) {
					em.setContent(em.getContent().substring(0, 15));
					lis.add(em);
				}else {
					lis.add(em);
				}
			}
			reList=lis;

		System.out.println("reList====" + reList);

		this.setResultpath("/Email/receiveemail.jsp");
		return "gotojsp";
	}

	// --------------------生成get和set方法的位置----------------------------------

	public String getEmailFileContentType() {
		return emailFileContentType;
	}

	public List<Email> getReList() {
		return reList;
	}

	public void setReList(List<Email> reList) {
		this.reList = reList;
	}

	public void setEmailFileContentType(String emailFileContentType) {
		this.emailFileContentType = emailFileContentType;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public String getResultpath() {
		return resultpath;
	}

	public void setResultpath(String resultpath) {
		this.resultpath = resultpath;
	}

	public List getEmp() {
		return emp;
	}

	public String getIds() {
		return ids;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getCurrent2() {
		return current2;
	}

	public void setCurrent2(Integer current2) {
		this.current2 = current2;
	}

	public void setEmp(List emp) {
		this.emp = emp;
	}

	public List getList() {
		return list;
	}

	public List<Email> getGiveemail() {
		return giveemail;
	}

	public void setGiveemail(List<Email> giveemail) {
		this.giveemail = giveemail;
	}

	public Integer getTitle() {
		return title;
	}

	public void setTitle(Integer title) {
		this.title = title;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String[] getAccessorys() {
		return accessorys;
	}

	public void setAccessorys(String[] accessorys) {
		this.accessorys = accessorys;
	}

	public Email getReceives() {
		return receives;
	}

	public void setReceives(Email receives) {
		this.receives = receives;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getUpPage() {
		return upPage;
	}

	public void setUpPage(Integer upPage) {
		this.upPage = upPage;
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

	public List<File> getEmailFile() {
		return emailFile;
	}

	public Email getLooks() {
		return looks;
	}

	public void setLooks(Email looks) {
		this.looks = looks;
	}

	public List<String> getEmailFileFileName() {
		return emailFileFileName;
	}

	public void setEmailFileFileName(List<String> emailFileFileName) {
		this.emailFileFileName = emailFileFileName;
	}

	public void setEmailFile(List<File> emailFile) {
		this.emailFile = emailFile;
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() throws Exception {
		return java.net.URLEncoder.encode(fileName, "UTF-8");
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	// ==============下面是是写方法的地方===========================

}
