package com.listme.operations;

import org.junit.Assert;
import org.junit.Test;

import com.listme.model.User;
import com.listme.model.UserList;
import com.listme.samples.SampleList;

public class TestStoreUserListOperation {
	
	@Test
	public void testExecuteStore() {
		GetUserOperation userOp = new GetUserOperation();
		userOp.setUsername("george.pagonis@gmail.com");
		userOp.execute();
		User user = userOp.getUser();
		StoreUserListOperation op = new StoreUserListOperation();
		SampleList slist= new SampleList();
		UserList ulist = slist.createMusicList();
		ulist.setUser(user);
		op.setUserList(ulist);
		op.execute();
		Assert.assertNotNull(op.getUserList().getCode());
	}

}
