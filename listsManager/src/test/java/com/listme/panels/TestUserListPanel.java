package com.listme.panels;

import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.listme.WicketApplication;
import com.listme.model.ListItem;
import com.listme.model.UserList;

public class TestUserListPanel extends WicketTester {
	private WicketTester tester;
	private UserList userList = new UserList();

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
		ListItem li1 = new ListItem();
		li1.setCode(1);
		li1.setDescription("Description for li 1");
		ListItem li2 = new ListItem();
		li1.setDescription("another Description");
		li2.setCode(2);
		Set<ListItem> listItems = new HashSet<ListItem>();
		listItems.add(li1);
		listItems.add(li2);
		userList.setTitle("List title");
		userList.setItems(listItems);
	}

	@Test
	public void testUserListPanel() {
		tester.startComponentInPage(new UserListPanel("test", userList));
		tester.assertComponent("test", UserListPanel.class);
	}
	
	
	

}
