package com.listme.persistence;

import org.junit.Assert;
import org.junit.Test;

public class TestHibernateUtil {

	@Test
	public void testGetSessionFactory() {
		Assert.assertNotNull(HibernateUtil.getSessionFactory());
	}

}
