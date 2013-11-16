package com.listme.model;

import java.awt.Image;

public class ListItem {
	
	private int code;
	
	private String url;
	
	private String description;
	
	private Image thumbnail;

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
	
	public Image getThumbnail() {
		return thumbnail;
	}
	
	public void setThumbnail(Image thumbnail) {
		this.thumbnail = thumbnail;
	}
	
}

