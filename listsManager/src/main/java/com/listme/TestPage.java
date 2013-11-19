package com.listme;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;

import com.listme.model.ListItem;
import com.listme.model.UserList;
import com.listme.operations.GetUserListsOperation;
import com.listme.panels.ListWall;

public class TestPage 
extends WebPage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String js = "alert('123');";
	
	public TestPage(){
		AjaxLink<String> link = new AjaxLink<String>("addElement") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				String newEls = "var $newEls = '<div class=\"element noble-gas nonmetal\"><h3>New List</h3><ul><li>ena</li><li>dyo</li><li>dfghfdkvh</li></ul></div>';";
				String ins = "$('#container').isotope( 'insert', $newEls ); return false;";
				js = newEls + ins;
				System.out.println(js);
				target.appendJavaScript(js);
			}
		};
		add(link);
		add(new ListWall("listWall", getUserWallList()));		
	}
	
	List<UserList> getUserWallList(){
		GetUserListsOperation op = new GetUserListsOperation();		
		op.execute();
		return op.getResults();
	}
	
	List<UserList> getMockUserWallList(){
		List<UserList> ul = new ArrayList<UserList>();
		ul.add(getList(1));
		ul.add(getList(2));
		ul.add(getList(3));
		ul.add(getList(4));
		ul.add(getList(5));
		return ul;
	}
	
	UserList getList(Integer code) {
		ListItem li1 = new ListItem();
		li1.setCode(1);
		li1.setDescription("Description for li 1");
		ListItem li2 = new ListItem();
		li1.setDescription("another Description");
		li2.setCode(2);
		Set<ListItem> listItems = new HashSet<ListItem>();
		listItems.add(li1);
		listItems.add(li2);
		UserList userList = new UserList();
		userList.setCode(code);
		userList.setTitle("List title");
		userList.setItems(listItems);
		return userList;
	}

}
