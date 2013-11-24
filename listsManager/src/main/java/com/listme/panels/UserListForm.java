package com.listme.panels;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.listme.TestPage;
import com.listme.model.User;
import com.listme.model.UserList;
import com.listme.operations.StoreUserListOperation;

public class UserListForm extends Form<UserList> {

	private static final long serialVersionUID = 1L;
	
	final List<IModel<com.listme.model.ListItem>> items = new ArrayList<IModel<com.listme.model.ListItem>>();

	final RefreshingView<com.listme.model.ListItem> lv;

	public UserListForm(String id, IModel<UserList> model) {
		super(id, new CompoundPropertyModel<UserList>(model));
		add(new TextField<String>("title"));
		// Create a panel within the form, to enable AJAX action
		final MarkupContainer rowPanel = new WebMarkupContainer("rowPanel");
		rowPanel.setOutputMarkupId(true);
		add(rowPanel);

		lv = new RefreshingView<com.listme.model.ListItem>("rows") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<com.listme.model.ListItem> item) {
				//int index = item.getIndex() + 1;
				TextField<com.listme.model.ListItem> description = new TextField<com.listme.model.ListItem>(
						"description", new PropertyModel(item.getModel(), "description"));
				description.setRequired(true);
				item.add(description);
			}

			@Override
			protected Iterator<IModel<com.listme.model.ListItem>> getItemModels() {
				return items.iterator();
			}
		};
		rowPanel.add(lv);
		lv.setItemReuseStrategy(new ReuseIfModelsEqualStrategy());

		AjaxSubmitLink addLink = new AjaxSubmitLink("addRow", this) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(AjaxRequestTarget target, Form form) {
				com.listme.model.ListItem li = new com.listme.model.ListItem();
				//li.setCode(items.size() + 1);
				li.setUrl("-");
				items.add(Model.of(li));
				if (target != null)
					target.add(rowPanel);
			}
		};
		addLink.setDefaultFormProcessing(false);
		rowPanel.add(addLink);

	}

	@Override
	protected void onSubmit() {
		Object o = getDefaultModelObject();
		if (o instanceof UserList) {
			//UserList newList = (UserList)o;
			UserList userList = (UserList)o;
			
			
			User user = new User();
			user.setUsername("george.pagonis@gmail.com");
			//UserList userList = new UserList();
			//userList.setTitle(newList.getTitle());
			userList.setUser(user);
			
			Iterator<Item<com.listme.model.ListItem>> gi = lv.getItems();
			while(gi.hasNext()){
				userList.getItems().add(gi.next().getModelObject());
			}
			
			StoreUserListOperation op = new StoreUserListOperation();
			op.setUserList(userList);
			op.execute();
			
			setResponsePage(TestPage.class);
		}
	}

}
