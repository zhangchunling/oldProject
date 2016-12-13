package com.zrgk.manu.service;

import java.util.List;

import com.zrgk.manu.bean.Manu_User;
import com.zrgk.manu.bean.PageFile;

public interface PageFileServiceInter {
	/**
	 * 新增pagefile
	 * @param pf
	 * @return boolean:true 添加成功
	 */
	boolean insertPageFile(PageFile pf,int uid);
	/**
	 * 查询pagefile(查询全部，条件查询)
	 * @param pf
	 * @return pageFile结果集
	 */
	List<PageFile> queryPageFile(PageFile pf);
	
	boolean deletePageFileByID(PageFile pf);
	
	PageFile queryPageFileByID(PageFile pf);
	
	Integer queryCountByPageFile(PageFile pf); 
	
	List<PageFile> queryPageFile();
	 PageFile queryPageFileByPUID(PageFile pf);
	 /**
	  * 查询待审核稿件
	  * @param pf
	  * @return
	  */
	 List<PageFile> queryWaitPageFile(PageFile pf,String iscount);
	 /**
	  * 查询已审核稿件
	  * @param pf
	  * @return
	  */
	 public List<PageFile> queryOkedPageFile(PageFile pf,String iscount);
	 /**
	  * 修改稿件的约稿ID
	  * @param pf
	  */
	 void updatePf_mu_idByID(PageFile pf,int uid);
	 
	 void updatePageFile(PageFile pf);
	
	 
}
