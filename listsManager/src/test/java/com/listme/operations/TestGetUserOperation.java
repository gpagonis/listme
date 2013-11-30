package com.listme.operations;

import org.junit.Assert;
import org.junit.Test;

public class TestGetUserOperation {

	@Test
	public void testExecute() {
		GetUserOperation op = new GetUserOperation();
		op.setUsername("george.pagonis@gmail.com");
		op.execute();
		Assert.assertNotNull(op.getUser());
	}

}
