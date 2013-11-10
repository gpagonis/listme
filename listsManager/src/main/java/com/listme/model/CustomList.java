package com.listme.model;

import java.util.ArrayList;
import java.util.List;

public class CustomList {

	private int code;
	
	private String shortDescription;
	
	private String title;
	
	private List<ListItem> items = new ArrayList<ListItem>();
	
	public String getShortDescription() {
		return shortDescription;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<ListItem> getItems() {
		return items;
	}
	
	public void setItems(List<ListItem> items) {
		this.items = items;
	}
	
}
