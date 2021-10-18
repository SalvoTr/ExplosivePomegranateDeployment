DROP DATABASE IF EXISTS library;

CREATE DATABASE library;
USE library;

CREATE TABLE book(
book_id INT (10) PRIMARY KEY,
isbn VARCHAR (17),
title VARCHAR (100),
description VARCHAR (5000),
year INT (4),
image BLOB
);

CREATE TABLE author(
author_id INT(10) PRIMARY KEY,
author_firstname VARCHAR (50),
author_lastname VARCHAR (30)
);

CREATE TABLE book_author(
book_id INT (10),
author_id INT (10),
FOREIGN KEY (book_id) REFERENCES book(book_id),
FOREIGN KEY (author_id) REFERENCES author(author_id)
);

CREATE TABLE category(
category_id INT(5) PRIMARY KEY,
category_name VARCHAR (50)
);

CREATE TABLE book_category(
book_id INT (10),
category_id INT (5),
FOREIGN KEY (book_id) REFERENCES book(book_id),
FOREIGN KEY (category_id) REFERENCES category(category_id)
);

CREATE TABLE role (
role_id Int (3) PRIMARY KEY,
name VARCHAR(100)
);

CREATE TABLE user (
user_id INT (10) PRIMARY KEY,
firstname VARCHAR(30),
lastname VARCHAR(30),
email VARCHAR(50),
Characteristics VARCHAR(200),
role_id INT (3),
FOREIGN KEY (role_id) REFERENCES role(role_id)
);

CREATE TABLE borrowed(
borrow_id INT (10) PRIMARY KEY,
user_id INT (10),
book_id INT (10),
startDate DATE,
initEndDate DATE,
extendEndDate DATE,
bookComment VARCHAR (600),
bookStatus BOOLEAN,
FOREIGN KEY (user_id) REFERENCES user(user_id),
FOREIGN KEY (book_id) REFERENCES book(book_id)
);

CREATE TABLE login (
login_id INT (10) PRIMARY KEY,
password VARCHAR(50)
);

DROP USER IF EXISTS 'Pomegranate'@'localhost';
CREATE USER 'Pomegranate'@'localhost' IDENTIFIED BY 'ExplosivePW2021';
USE library;
GRANT ALL PRIVILEGES ON library.* TO 'Pomegranate'@'localhost';