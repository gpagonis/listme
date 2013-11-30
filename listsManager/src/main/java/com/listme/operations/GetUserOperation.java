package com.listme.operations;

import org.hibernate.Session;

import com.listme.model.User;
import com.listme.persistence.HibernateUtil;

public class GetUserOperation {

	private User user;
	
	private String username;
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public User getUser() {
		return user;
	}
	
	public void execute(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		user = (User) session.get(User.class, username);
	}
	
}
