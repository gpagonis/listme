package com.listme.samples;

import com.listme.model.ListItem;
import com.listme.model.Tag;
import com.listme.model.UserList;

public class SampleList {

	public UserList createMusicList(){
		UserList musicList = new UserList();
		musicList.setTitle("Rock punk Bands");
		addMusicTags(musicList);
		addMusicItems(musicList);
		return musicList;
	}
	
	private void addMusicTags(UserList musicList){
		Tag tag1 = new Tag();
		tag1.setName("rock");
		Tag tag2 = new Tag();
		tag2.setName("punk");
		musicList.addTag(tag1);
		musicList.addTag(tag2);
	}
	
	private void addMusicItems(UserList musicList){
		ListItem item1 = new ListItem();
		item1.setDescription("Ramones");
		ListItem item2 = new ListItem();
		item2.setDescription("Sex pistols");
		musicList.addItem(item1);
		musicList.addItem(item2);
	}
	
}
