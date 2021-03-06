package com.listme.panels;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import com.listme.model.UserList;

public class CreateUserListPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateUserListPanel(String id) {
		super(id);
		setOutputMarkupId(true);
		add(new UserListForm("form", new CompoundPropertyModel<UserList>(new UserList())));
	}

}
