-- ==============================================================
-- ST4Example DB creation script for MySQL
-- ==============================================================
SET NAMES utf8;

DROP DATABASE IF EXISTS st4db;
CREATE DATABASE st4db CHARACTER SET utf8 COLLATE utf8_bin;

USE st4db;
-- --------------------------------------------------------------
-- ROLES
-- users roles
-- --------------------------------------------------------------

CREATE TABLE roles(

-- id has the INTEGER type (other name is INT), it is the primary key
	id INTEGER NOT NULL PRIMARY KEY,

-- name has the VARCHAR type - a string with a variable length
-- names values should not be repeated (UNIQUE)
	name VARCHAR(10) NOT NULL UNIQUE
);

-- this two commands insert data into roles table
-- --------------------------------------------------------------
-- ATTENTION!!!
-- we use ENUM as the Role entity, so the numeration must started
-- from 0 with the step equaled to 1
-- --------------------------------------------------------------
INSERT INTO roles VALUES(0, 'admin');
INSERT INTO roles VALUES(1, 'client');
INSERT INTO roles VALUES(2, 'manager');
-- --------------------------------------------------------------
-- USERS
-- --------------------------------------------------------------
CREATE TABLE users(

	id INTEGER NOT NULL auto_increment PRIMARY KEY,

-- 'UNIQUE' means logins values should not be repeated in login column of table
	login VARCHAR(20) NOT NULL UNIQUE,

-- not null string columns
	password VARCHAR(20) NOT NULL,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,

-- this declaration contains the foreign key constraint
-- role_id in users table is associated with id in roles table
-- role_id of user = id of role
	role_id INTEGER NOT NULL REFERENCES roles(id)

-- removing a row with some ID from roles table implies removing
-- all rows from users table for which ROLES_ID=ID
-- default value is ON DELETE RESTRICT, it means you cannot remove
-- row with some ID from the roles table if there are rows in
-- users table with ROLES_ID=ID
		ON DELETE CASCADE

-- the same as previous but updating is used insted deleting
		ON UPDATE RESTRICT
);

-- id = 1
INSERT INTO users VALUES(DEFAULT, 'admin', 'admin', 'Ivan', 'Ivanov', 0);
-- id = 2
INSERT INTO users VALUES(DEFAULT, 'client', 'client', 'Petr', 'Petrov', 1);
-- id = 3
INSERT INTO users VALUES(DEFAULT, 'manager', 'manager', 'Arsen', 'Arsenov', 2);

-- --------------------------------------------------------------
-- STATUSES
-- statuses for orders
-- --------------------------------------------------------------
CREATE TABLE statuses(
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(10) NOT NULL UNIQUE
);

-- --------------------------------------------------------------
-- ATTENTION!!!
-- we use ENUM as the Status entity, so the numeration must started
-- from 0 with the step equaled to 1
-- --------------------------------------------------------------
INSERT INTO status VALUES(0, 'BLOCKING');
INSERT INTO status VALUES(1, 'UNBLOCKING');



-- --------------------------------------------------------------
-- ORDERS
-- --------------------------------------------------------------
CREATE TABLE orders(
	id INTEGER NOT NULL auto_increment PRIMARY KEY,
	bill INTEGER NOT NULL DEFAULT 0,
	user_id INTEGER NOT NULL REFERENCES users(id),
	status_id INTEGER NOT NULL REFERENCES status(id)
);

-- bill = 0; user_id=2; status_id=0
INSERT INTO orders VALUES(DEFAULT, 0, 0, 0);
-- bill = 0; user_id=2; status_id=1
INSERT INTO orders VALUES(DEFAULT, 0, 1, 1);

-- --------------------------------------------------------------
-- MENU
-- --------------------------------------------------------------
CREATE TABLE menu(
	id INTEGER NOT NULL auto_increment PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	price INTEGER NOT NULL,
	category VARCHAR(50) NOT NULL
);

INSERT INTO menu VALUES(DEFAULT, 'Mazda6', 200, 'D');
INSERT INTO menu VALUES(DEFAULT, 'Wolksvagen Polo', 75, 'B');
INSERT INTO menu VALUES(DEFAULT, 'Daewoo Matiz', 25, 'A');
INSERT INTO menu VALUES(DEFAULT, 'Toyota Corolla', 100, 'C');

-- --------------------------------------------------------------
-- ORDERS_MENU
-- relation between orders and menu
-- each row of this table represents one menu item
-- --------------------------------------------------------------
CREATE TABLE orders_menu(
	order_id INTEGER NOT NULL REFERENCES orders(id),
	menu_id INTEGER NOT NULL REFERENCES menu(id)
);

INSERT INTO orders_menu VALUES(1, 1);
INSERT INTO orders_menu VALUES(1, 7);
INSERT INTO orders_menu VALUES(1, 5);

INSERT INTO orders_menu VALUES(2, 1);
INSERT INTO orders_menu VALUES(2, 7);
	
-- --------------------------------------------------------------
-- test database:
-- --------------------------------------------------------------
SELECT * FROM orders_menu;
SELECT * FROM menu;
SELECT * FROM orders;
SELECT * FROM status;
SELECT * FROM users;
SELECT * FROM roles;
