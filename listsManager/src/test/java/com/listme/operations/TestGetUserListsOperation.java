package com.listme.operations;

import org.junit.Assert;
import org.junit.Test;

public class TestGetUserListsOperation {

	@Test
	public void testExecute() {
		GetUserListsOperation op = new GetUserListsOperation();		
		op.execute();
		Assert.assertNotNull(op.getResults());
		System.out.println(op.getResults().size());
	}

}
