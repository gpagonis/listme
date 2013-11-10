package com.listme.model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private Image profileImg;
	
	List<CustomList> myLists = new ArrayList<CustomList>();
	
	List<CustomList> favoriteLists = new ArrayList<CustomList>();
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Image getProfileImg() {
		return profileImg;
	}
	
	public void setProfileImg(Image profileImg) {
		this.profileImg = profileImg;
	}
	
	public List<CustomList> getMyLists() {
		return myLists;
	}
	
	public void setMyLists(List<CustomList> myLists) {
		this.myLists = myLists;
	}
	
	public List<CustomList> getFavoriteLists() {
		return favoriteLists;
	}
	
	public void setFavoriteLists(List<CustomList> favoriteLists) {
		this.favoriteLists = favoriteLists;
	}

}
