package com.zrgk.manu.dao;

import java.util.List;

import com.zrgk.manu.bean.PageFile;

public interface PageFileDaoInter {
	/**
	 * 新增pagefile
	 * @param pf
	 * @return boolean:true 添加成功
	 */
	boolean insertPageFile(PageFile pf);
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
	
	PageFile queryPageFileByPF_mu_id(PageFile pf);
	/**
	 * 查看待审核 已审核 二次审核稿件 根据auditNum 2 未审核 3 一审  4 二审 
	 * @param pf
	 * @param auditNum
	 * @return
	 */
	List<PageFile> queryWaitPageFile(PageFile pf,String auditNum,String iscount );
	/**
	 * 根据稿件主键修改稿件约稿属性
	 * @param pf
	 */
	void updatePageFilePf_mu_id(PageFile pf);
	
	
	void updatePageFile(PageFile pf);
	
	
}
