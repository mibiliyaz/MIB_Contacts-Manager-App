create database if not exists ContactsManager;
use ContactsManager;

create table if not exists Users(
	uid int not null auto_increment,
	name varchar(25),
	loginid varchar(25) not null,
	pswd varchar(15) not null,
	secret varchar(20) not null,
	primary key(uid));

create table if not exists Contacts(
	id int not null auto_increment,
	name varchar(25),
	num varchar(10),
	mail varchar(25),
	addr varchar(150),
	dob varchar(15),
	notes varchar(500),
	uid int,
	primary key(id), foreign key(uid) references Users(uid) ON DELETE CASCADE ON UPDATE CASCADE);