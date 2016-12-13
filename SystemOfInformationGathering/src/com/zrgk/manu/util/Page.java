package com.zrgk.manu.util;

import java.io.Serializable;

/**
 * 这个类封装了分页所需的字段
 * @author bleach
 *
 */
public class Page implements Serializable{
	
	
	private static final long serialVersionUID = 6240734817539734101L;
		private int totalPage;//一共可以分多少页
		private int pageSize=5;//每页显示的条数
		private int count;//数据总条数
		private Integer currentPage=1;	//当前页数
	
		
		
		
		public Integer getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(Integer currentPage) {
			this.currentPage = currentPage;
		}
		public int getTotalPage() {
			return count%pageSize==0?count/pageSize:count/pageSize+1;//计算出总页数
		}
		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}

}
