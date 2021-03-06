drop table users_followed;
drop table lists_followed;
drop table list_item;
drop table list_;
drop table user_;

create table user_ (
	username varchar(50) not null,
	fname varchar(50),
	lname varchar(50),
	pic blob,
	primary key (username)
);

create table list_ (
	code int(10) UNSIGNED not null AUTO_INCREMENT,
	title varchar(128) not null,
	username varchar(50) not null,
	primary key (code),
	foreign key (username) references user_(username)
);

create table list_item (
	code int(10) UNSIGNED not null AUTO_INCREMENT,
	list_code int(10) UNSIGNED not null,
	descr varchar(1024) not null,
	url varchar(140),
	pic blob,
	primary key (code),
	foreign key (list_code) references list_(code)
);

create table lists_followed (
	username varchar(50) not null,
	list_code int(10) UNSIGNED not null,
	primary key (username, list_code),
	foreign key (username) references user_(username),
	foreign key (list_code) references list_(code)
);

create table users_followed (
	user1 varchar(50) not null,
	user2 varchar(50) not null,
	primary key (user1, user2),
	foreign key (user1) references user_(username),
	foreign key (user2) references user_(username)
);

create table tags (
	tag_name varchar(124) not null,
	primary key (tag_name)
);

create table list_tag(
	tag_name varchar(124) not null,
	list_code int(10) UNSIGNED not null,
	primary key (tag_name, list_code),
	foreign key (tag_name) references tags(tag_name),
	foreign key (list_code) references list_(code)
);
 