package com.zrgk.manu.service;

import java.util.List;

import com.zrgk.manu.bean.Manu;
import com.zrgk.manu.bean.Manu_User;
import com.zrgk.manu.bean.PageFile;
import com.zrgk.permission.model.Employee;



public interface MyManuServiceInter {
	/**
	 * 根据用户查询mu列表
	 * @param mu
	 * @param uid
	 * @return
	 */
	List<Manu> queryMyManuByUid(Manu mu,int uid);
	
	/**
	 * 根据mid 删除(拒绝约稿)
	 * @param Manu2Excel mu
	 * @return
	 */
	boolean deleteMyManuByID(int id);
	/**
	 * 根据MID查询
	 * @param mu
	 * @return
	 */
	Manu queryMyManuByID(int id);
	
	Integer queryCountByManu(Manu mu);
	/**
	 * 查询未接受的约稿
	 * @param mu
	 * @param uid
	 * @return
	 */
	List<Manu> queryMyManuByUstateNotAccept(Manu mu,int uid);
	/**
	 * 查询已接受的约稿
	 * @param mu
	 * @param uid
	 * @return
	 */
	List<Manu> queryMyManuByUstateAccept(Manu mu,int uid);
	
	List<Manu> queryMyManuByUstateSubed(Manu mu,int uid);
	
	public void updateState(Manu_User muu);
	/**
	 * 修改中间表里的发布状态为-1 代表已拒绝 
	 * @param muu
	 */
	void updateUmstate(Manu_User muu);
	
	Integer queryMyManuCount(Manu mu,int uid,String accept);
	
	
	public List<Manu> queryAllManu(int uid,int accept) ;
	
	void updateState2Sub(Manu_User muu);
	
	public List<Manu> queryNotExitsPageFileOfManus(int uid);
	/**
	 * 根据mid 返回指定约稿下是否有稿件
	 * @param muu
	 * @return 有 true 没有fasle
	 */
	public boolean queryManuIsPf(Manu_User muu,int uid);
	
	/**
	 * 查询待审核约稿
	 * @param mu
	 * @param uid
	 * @return
	 */
	List<Manu> queryManu(Manu mu,int state,String iscount);
	/**
	 * 根据Id修改发布状态 及删除约稿【状态置为-1】
	 * @param mu
	 * @param model 0 为发布 -1为删除
	 * @return 成功 失败
	 */
	boolean updateMstateByMid(Manu mu,int model);
	/**
	 * 根据ID查询MANU
	 * @param mu
	 * @return
	 */
	Manu queryManuById(Manu mu);
	
	void inserManu(Manu mu);
	/**
	 * 修改中间表状态为3 审核通过 
	 * @param muu
	 */
	void updateState3Sub(Manu_User muu) ;
	/**
	 * 修改中间表状态为4 已录用 
	 * @param muu
	 */
	void updateState4Sub(Manu_User muu);
	/**
	 * 新增稿件约稿中间表
	 * @param muu
	 */
	public void insertManu_User(Manu_User muu);
	
	/**
	 * 查询所有用户
	 * @return
	 */
		List<Employee> queryAllEmp();
	/**
	 * 新增约稿与约稿用户中间表
	 * @return
	 */
	boolean addManuAndManu_User(Manu mu,String users);
	
	/**
	 * 修改状态为1审核不通过
	 * @param muu
	 */
	void updateStateNosub(Manu_User muu);

	/**
	 * 修改状态为2审核不通过
	 * @param muu
	 */
	public void updateStateNoOk(Manu_User muu);
}
