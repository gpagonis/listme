package com.listme.operations;

import org.hibernate.Session;

import com.listme.model.User;
import com.listme.persistence.HibernateUtil;

public class StoreUserOperation {

	private User user;
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public void execute(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(user);
		session.getTransaction().commit();
	}
	
}
