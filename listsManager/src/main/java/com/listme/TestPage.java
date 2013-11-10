package com.listme;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class TestPage 
extends WebPage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TestPage(){
		add(new Label(("hello"), "hello world"));
	}

}
