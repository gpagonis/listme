package com.listme.oauth.facebook;

import org.junit.Test;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;

public class TestFacebookIntegration {

	//@Test
	public void testConnect(){
		FacebookClient facebookClient = new DefaultFacebookClient("CAACEdEose0cBAJBVR2C3Xyqmq9M04P0ZCEBZA949J2uE0TJ96zoJ3Jq1JHD2aJp9ZBKqcyIQVsI0tEnTUPcp5F6ZBr5E49RZCOOHM7H2o5Xp7cMxDxFm8slZAcrF5qtud2xLFrVQ7vsBwS9se8iEsGHH0P6uehBg3xbSf0wbiQZCOYh27Lu8xdolkiebiAzlD3XLpLp5uPaoAZDZD");
		User user = facebookClient.fetchObject("me", User.class);
		System.out.println(user.getLastName());
	}
	
}
