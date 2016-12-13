package com.zrgk.manu.dao;

import java.util.List;

import com.zrgk.manu.bean.Manu;
import com.zrgk.manu.bean.Manu_User;
import com.zrgk.manu.bean.PageFile;
import com.zrgk.permission.model.Employee;

public interface MyManuDaoInter {
	/**
	 * 根据用户查询mu列表
	 * @param mu
	 * @param uid
	 * @return
	 */
	List<Manu> queryMyManu(Manu mu ,int uid,String accept);
	
	/**
	 * 根据mid 删除(拒绝约稿)
	 * @param Manu2Excel mu
	 * @return
	 */
	boolean deleteMyManuByID(Manu mu);
	/**
	 * 根据MID查询
	 * @param mu
	 * @return
	 */
	Manu queryMyManuByID(int id);
	
	Integer queryCountByManu(Manu mu);
	
	void updateStateById(Manu_User muu);
	
	public Manu_User queryStateByUidAndMid(Manu_User muu);
	
	/**
	 * 查询符合条件的总条数
	 * @param mu
	 * @return count
	 */
	Integer queryMyManuCount(Manu mu,int uid,String accept);
	/**
	 * 查询所有
	 * @param uid
	 * @param accept
	 * @return
	 */
	List<Manu> queryAllManu(int uid,int accept);
	
	List<Manu> queryNotExitsPageFileOfManus(int uid);
	//查询指定约稿是否绑定稿件
	/**
	 * 查询是否有指定mid约稿是否有绑定好的稿件，有稿件返回TRUE 没有返回FALSE
	 * @param muu
	 * @param uid
	 * @return 有稿件：TURE 没稿件：FASLE
	 */
	public boolean queryManuIsPf(Manu_User muu,int uid) ;
	/**
	 * 根据uid 与 mid 查询中间表
	 * @param muu
	 */
	Manu_User queryManu_UserByUidAndMid(Manu_User muu);
	/**
	 * 通过MUID修改PFID
	 * @param muu
	 */
	void updateManu_UserPfidByMuid(Manu_User muu);
	/**
	 * 查询未发布约稿
	 * @param mu
	 * @param uid
	 * @return
	 */
	List<Manu> queryManu(Manu mu,String iscount);
	/**
	 * 查询待审核约稿
	 * @param mu
	 * @return
	 */
	List<Manu> queryWaitManu(Manu mu,String iscount);
	
	/**
	 * 已审核
	 * @param mu
	 * @return
	 */
	public List<Manu> queryOkedManu(Manu mu,String iscount);
	
	void updateMstateByMid(Manu mu);
	/**
	 * 根据主键ID查询 	Manu
	 * @param mu
	 * @return
	 */
	Manu queryManuById(Manu mu);
	
	void insertManu(Manu mu);
	/**
	 * 新增稿件约稿关系中间表
	 * @param muu
	 */
	void insertManu_User(Manu_User muu);
/**
 * 查询所有用户
 * @return
 */
	List<Employee> queryAllEmp();
	
	/**
	 * 修改稿件状态通过稿件ID
	 * @param pf
	 */
	void updatePfStateById(PageFile pf);
	
	PageFile queryPageFileById(PageFile pf);
}

