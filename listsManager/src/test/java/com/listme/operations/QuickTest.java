package com.listme.operations;

import org.hibernate.Session;
import org.junit.Test;

import com.listme.model.UserList;
import com.listme.persistence.HibernateUtil;

public class QuickTest {

	@Test
	public void testExecuteDelete() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		UserList userList = (UserList) session.get(UserList.class, 107);
		if (userList != null) {
			session.beginTransaction();
			session.delete(userList);
			session.getTransaction().commit();
		}
	}
}
