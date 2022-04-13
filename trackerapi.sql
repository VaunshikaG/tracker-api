drop database trackerdb;
drop user tracker;
create user tracker with password 'password';
create database trackerdb with template=template0 owner=tracker;
\connect trackerdb;
alter default privileges grant all on tables to tracker;
alter default privileges grant all on sequences to tracker;

create table t_users(
user_id integer primary key not null,
first_name varchar(20) not null,
last_name varchar(20) not null,
email varchar(30) not null,
password text not null
);

create table t_categories(
category_id integer primary key not null,
user_id integer not null,
title varchar(20) not null,
description varchar(50) not null
);
alter table t_categories add constraint cat_users_fk
foreign key (user_id) references t_users(user_id);

create table t_transactions(
transaction_id integer primary key not null,
category_id integer not null,
user_id integer not null,
amount numeric(10,2) not null,
note varchar(50) not null,
transaction_date bigint not null
);

alter table t_transactions add constraint trans_cat_fk
foreign key (category_id) references t_categories(category_id);
alter table t_transactions add constraint trans_users_fk
foreign key (user_id) references t_users(user_id);

create sequence t_users_seq increment 1 start 1;
create sequence t_categories_seq increment 1 start 1;
create sequence t_transactions_seq increment 1 start 1000;