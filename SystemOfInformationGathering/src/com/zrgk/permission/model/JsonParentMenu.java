package com.zrgk.permission.model;

import java.util.List;

//json字符串需要的存有菜单信息的对象
public class JsonParentMenu {
	private String text;
	private List<JsonChildMenu> items;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<JsonChildMenu> getItems() {
		return items;
	}
	public void setItems(List<JsonChildMenu> items) {
		this.items = items;
	}
	

}
