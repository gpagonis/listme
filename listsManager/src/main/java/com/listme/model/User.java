package com.listme.model;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User 
implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private Image profileImg;
	
	List<UserList> favoriteLists = new ArrayList<UserList>();
	
	List<User> favoriteUsers = new ArrayList<User>();
	
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
	
	public List<UserList> getFavoriteLists() {
		return favoriteLists;
	}
	
	public void setFavoriteLists(List<UserList> favoriteLists) {
		this.favoriteLists = favoriteLists;
	}
	
	public List<User> getFavoriteUsers() {
		return favoriteUsers;
	}
	
	public void setFavoriteUsers(List<User> favoriteUsers) {
		this.favoriteUsers = favoriteUsers;
	}

}
