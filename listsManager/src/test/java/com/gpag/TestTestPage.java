package com.gpag;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.listme.TestPage;
import com.listme.WicketApplication;

/**
 * Simple test using the WicketTester
 */
public class TestTestPage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(TestPage.class);

		//assert rendered page class
		tester.assertRenderedPage(TestPage.class);
	}
}
