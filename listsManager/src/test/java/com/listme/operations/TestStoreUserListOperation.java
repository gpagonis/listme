package com.listme.operations;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.listme.model.ListItem;
import com.listme.model.User;
import com.listme.model.UserList;

public class TestStoreUserListOperation {

	private UserList userList = getUserList();
	
	@Test
	public void testExecuteStore() {
		StoreUserListOperation op = new StoreUserListOperation();
		op.setUserList(userList);
		op.execute();
		Assert.assertNotNull(op.getUserList().getCode());
	}
	

	private UserList getUserList(){
		UserList userList = new UserList();		
		userList.setUser(getUser());
		userList.setTitle("testList new");
		userList.setItems(getListItems());
		
		return userList;
	}
	
	private Set<ListItem> getListItems(){
		Set<ListItem> list = new HashSet<ListItem>();
		list.add(createListItem(1,"Jennie Finch @jfinch27"));
		list.add(createListItem(2, "Victoria Azarenka @vichka35"));
		list.add(createListItem(3, "Angela Rypien @angela_rypien"));
		list.add(createListItem(4, "Paula Creamer @paulacreamer1"));
		return list;
	}
	
	private ListItem createListItem(int code, String description){
		ListItem item = new ListItem();
		//item.setCode(code);
		item.setDescription(description);
		item.setUrl("-");
		return item;
	}
	
	private User getUser(){
		User user = new User();
		user.setUsername("george.pagonis@gmail.com");
		user.setFirstName(" ");
		user.setLastName(" ");
		return user;
	}

}
