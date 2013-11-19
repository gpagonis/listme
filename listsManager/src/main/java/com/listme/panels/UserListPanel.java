package com.listme.panels;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import com.listme.model.ListItem;
import com.listme.model.UserList;

/**
 * List the {@link ListItem}s.
 * 
 */
public class UserListPanel extends Panel {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UserListPanel(String id, UserList userList) {
		super(id);
		add(new Label("title", userList.getTitle()));

		RepeatingView view = new RepeatingView("listItems");
		add(view);
		for (ListItem listItem : userList.getItems()) {
			WebMarkupContainer list = new WebMarkupContainer(view.newChildId());
			list.add(new Label("listItem", listItem.getDescription()));
			view.add(list);
		}

	}

}
