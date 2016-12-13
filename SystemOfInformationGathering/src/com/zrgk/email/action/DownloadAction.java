package com.zrgk.email.action;
 
  
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 下载附件的action
 * 
 * @author wfh
 * 
 *  2015-7-29
 * 
 */

public class DownloadAction extends ActionSupport {

	 
	private static final long serialVersionUID = 2185889530451770627L;
//	接受文件名的属性filename
	private String filename;
	 
	public String getFilename() throws Exception {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

// 得到InputStream方法
	public InputStream getDownloadFile() throws Exception {

		return ServletActionContext.getServletContext().getResourceAsStream(
				"/upload/" + filename);
 
	}

//	下载要走的默方法,在这里可以写业务代码
	@Override
	public String execute() throws Exception {
 
		 
			return this.SUCCESS;
		  
	}

}
