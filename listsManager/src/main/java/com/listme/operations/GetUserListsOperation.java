package com.listme.operations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.listme.model.UserList;
import com.listme.persistence.HibernateUtil;

public class GetUserListsOperation {

	private List<UserList> results = new ArrayList<UserList>();
	
	public List<UserList> getResults() { 
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public void execute(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from com.listme.model.UserList");
		results.addAll(query.list());
	}
	
}
