package com.listme.panels;

import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import com.listme.model.ListItem;
import com.listme.model.UserList;

/**
 * List the {@link ListItem}s.
 *
 */
public class ListWall extends Panel {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ListWall(String id, List<UserList> userLists) {
		super(id);		
		WebMarkupContainer container = new WebMarkupContainer("container");
		add(container);
		RepeatingView userListsRView = new RepeatingView("userLists");
		container.add(userListsRView);
		for (UserList userList : userLists) {			
			userListsRView.add(new UserListPanel(userListsRView.newChildId(),userList));
		}

	}

}
