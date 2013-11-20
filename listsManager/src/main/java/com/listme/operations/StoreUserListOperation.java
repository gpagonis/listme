package com.listme.operations;

import org.hibernate.Session;

import com.listme.model.UserList;
import com.listme.persistence.HibernateUtil;

public class StoreUserListOperation {

	private UserList userList;
	
	public void setUserList(UserList userList) {
		this.userList = userList;
	}
	
	public UserList getUserList() {
		return userList;
	}
	
	public void execute(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(userList);
		session.getTransaction().commit();
	}
	
}
