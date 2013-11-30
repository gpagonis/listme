package com.listme.model;

import java.io.Serializable;

public class Tag implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || (!(obj instanceof Tag))){
			return false;
		}
		Tag t = (Tag) obj;
		return name.equals(t.getName());
	}
	
	@Override
	public int hashCode() {
		int tmp = 0;
		if (name != null) {	    	  
			tmp = name.hashCode();
		} 
		return tmp;
	}
	
}
