package com.zrgk.utils;

import java.io.Serializable;

public class Page {

	
	private int currentPage = 1;

	private int pageSize = 5;

	private int totalSize;

	private int totalPage;
	
	

	public int getTotalPage() {
		return totalSize%pageSize==0?totalSize/pageSize:totalSize/pageSize+1;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	
	 
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
