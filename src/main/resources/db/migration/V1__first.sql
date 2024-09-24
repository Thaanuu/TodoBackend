CREATE TABLE IF NOT EXISTS USER_PROFILE(
id int not null AUTO_INCREMENT PRIMARY KEY,
name varchar(20),
pass varchar(20)
);
CREATE TABLE IF NOT EXISTS TODO(
id int not null AUTO_INCREMENT PRIMARY KEY,
user_id int not null,
todo_text varchar(100),
description varchar(100),
deadline varchar(20),
status varchar(20) default 'open',
priority boolean default false
);
