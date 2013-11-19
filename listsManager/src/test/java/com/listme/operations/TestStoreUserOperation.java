package com.listme.operations;

import org.junit.Assert;
import org.junit.Test;

import com.listme.model.User;

public class TestStoreUserOperation {

	private User user = getUser();
	
	@Test
	public void testExecuteStore() {
		StoreUserOperation op = new StoreUserOperation();
		op.setUser(user);
		op.execute();
		Assert.assertNotNull(op.getUser());
	}
	
	@Test
	public void testExecuteUpdate() {
		user.setFirstName("george");
		user.setLastName("pagonis");
		StoreUserOperation op = new StoreUserOperation();
		op.setUser(user);
		op.execute();
		Assert.assertNotNull(op.getUser().getFirstName());
	}
	
	private User getUser(){
		User user = new User();
		user.setUsername("george.pagonis@gmail.com");
		user.setFirstName(" ");
		user.setLastName(" ");
		return user;
	}

}
