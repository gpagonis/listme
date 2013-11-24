package com.listme.model;

import java.awt.Image;
import java.io.Serializable;

public class ListItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	public boolean equals(Object obj) {
	      if (obj == null) return false;
	      if (!this.getClass().equals(obj.getClass())) return false;

	      ListItem obj2 = (ListItem)obj;
	      
	      if(this.description != null && (this.description.equals(obj2.getDescription())))
	      //if((this.code == obj2.getCode()))
	      {
	         return true;
	      }
	      return false;
	   }
	   public int hashCode() {
	      int tmp = 0;
	      if (description != null) {	    	  
	    	  tmp = description.hashCode();
	      } 
	      return tmp;
	   }
	
}

