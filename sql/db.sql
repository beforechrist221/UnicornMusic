drop database if exists unicorn_db;
create database unicorn_db;

drop table if exists unicorn_db.user;
create table unicorn_db.user(
 id int auto_increment primary key
  comment 'id PK',
  email varchar(255) not null unique
  comment 'email NN UN',
  username varchar(255) not null
  comment 'username NN',
  password varchar(255) not null
  comment 'password NN'
);
drop table if exists unicorn_db.article;
create table unicorn_db.article(
 id int auto_increment primary key
 comment 'id PK',
 title varchar(255) not null
 comment 'title NN',
 content varchar(255) not null
 comment 'content NN'
);

select *
from unicorn_db.user;

select *
from unicorn_db.article;


truncate unicorn_db.user;

truncate unicorn_db.article;