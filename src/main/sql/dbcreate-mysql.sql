-- ==============================================================
-- ST4Example DB creation script for MySQL
-- ==============================================================
SET NAMES utf8;

DROP DATABASE IF EXISTS `st4db`;
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

-- --------------------------------------------------------------
-- ORDERS
-- --------------------------------------------------------------
CREATE TABLE orders(
                       id INTEGER NOT NULL auto_increment PRIMARY KEY,
                       bill INTEGER NOT NULL DEFAULT 0,
                       user_id INTEGER NOT NULL REFERENCES users(id)

);


INSERT INTO orders VALUES(DEFAULT, 0, 0);
-- bill = 0; user_id=2; status_id=1
INSERT INTO orders VALUES(DEFAULT, 0, 1);

-- --------------------------------------------------------------
-- MENU
-- --------------------------------------------------------------
CREATE TABLE cars
(
    id INTEGER NOT NULL auto_increment PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    price INTEGER NOT NULL,
    category VARCHAR(50) NOT NULL
);

INSERT INTO cars VALUES(DEFAULT, 'Mazda6', 200, 'D');
INSERT INTO cars VALUES(DEFAULT, 'Wolksvagen Polo', 75, 'B');
INSERT INTO cars VALUES(DEFAULT, 'Daewoo Matiz', 25, 'A');
INSERT INTO cars VALUES(DEFAULT, 'Toyota Corolla', 100, 'C');

-- --------------------------------------------------------------
-- ORDERS_CARS
-- relation between order and car
-- each row of this table represents one cars item
-- --------------------------------------------------------------
CREATE TABLE orders_cars(
                            order_id INTEGER NOT NULL REFERENCES orders(id),
                            car_id INTEGER NOT NULL REFERENCES cars (id)
);

INSERT INTO orders_cars VALUES(1, 1);
INSERT INTO orders_cars VALUES(1, 7);
INSERT INTO orders_cars VALUES(1, 5);

INSERT INTO orders_cars VALUES(2, 1);
INSERT INTO orders_cars VALUES(2, 7);

-- --------------------------------------------------------------
-- test database:
-- --------------------------------------------------------------
SELECT * FROM orders_cars;
SELECT * FROM cars;
SELECT * FROM orders;
SELECT * FROM users;
SELECT * FROM roles;

