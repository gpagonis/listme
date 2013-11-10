package com.listme.model;

public class ListItem {
	
	private int code;
	
	private String url;
	
	private String description;

	public String getUrl() {
		return url;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}

