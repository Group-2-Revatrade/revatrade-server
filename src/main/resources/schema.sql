CREATE TABLE users(
userId int unsigned not null auto_increment Primary key, 
name varchar(50) not null, 
username varchar(50) not null unique, 
password varchar(50) not null unique
);