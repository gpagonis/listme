package com.listme.model;

import java.util.HashSet;
import java.util.Set;

public class UserList {

	private int code;
	
	private User user;
	
	private String title;
	
	private Set<ListItem> items = new HashSet<ListItem>();
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Set<ListItem> getItems() {
		return items;
	}
	
	public void setItems(Set<ListItem> items) {
		this.items = items;
	}
	
}
