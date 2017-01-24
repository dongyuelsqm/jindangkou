create table products(id int primary key AUTO_INCREMENT, name VARCHAR(100) not null, department_id int not null, unit_price float not null, description VARCHAR(500), pictures VARCHAR(200), videos VARCHAR(200),  minimum int, postal VARCHAR(10), code VARCHAR(20), input_date datetime) character set = utf8;
create table product_quantity(id int primary key AUTO_INCREMENT, product_id int, color VARCHAR(10), size VARCHAR(10), number int) character set = utf8;
create table orders(id int primary key AUTO_INCREMENT, product_id int not null, product_number int not null, discount float, customer_id int not null, address_id int not null, deal_date datetime, ship_id VARCHAR(200), state int) character set = utf8;
create table sub_orders(id int primary key AUTO_INCREMENT, product_id int, order_id int, color_id VARCHAR(10), size_id VARCHAR(10), number int) character set = utf8;
create table addresses(id int primary key not null AUTO_INCREMENT, open_id VARCHAR(100) not null, name VARCHAR(100), phone VARCHAR(100), province VARCHAR(100) not null, city VARCHAR(100) not null, district VARCHAR(100) not null, detail VARCHAR(100)) character set = utf8;
create table department(department_id int not null AUTO_INCREMENT, name VARCHAR(100)) character set = utf8;
create table inventory(id int PRIMARY KEY AUTO_INCREMENT,product_id int, color VARCHAR(100), number int) character set = utf8;
create table admin(id int primary key AUTO_INCREMENT, username VARCHAR(100), password VARCHAR(100)) character set = utf8;
create table labels(id int primary key AUTO_INCREMENT, title VARCHAR(100)) character set = utf8;
create table product_label_links(id int AUTO_INCREMENT primary key, product_id int, label int) character set = utf8;
CREATE TABLE colors(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100)) character set = utf8;
CREATE TABLE sizes(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100)) character set = utf8;
CREATE TABLE departments(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100)) character set = utf8;
CREATE TABLE collections(id INT PRIMARY KEY AUTO_INCREMENT, product_id INT, open_id INT) character set = utf8;
CREATE TABLE shoping_cart(id INT PRIMARY KEY AUTO_INCREMENT, product_id INT, open_id INT, color_id INT, size_id INT, number INT) character set = utf8;
CREATE TABLE notifications(id int primary key AUTO_INCREMENT, title VARCHAR(100), created_at DATETIME) character set = utf8;
CREATE TABLE customers(id int primary key AUTO_INCREMENT, open_id VARCHAR(100) NOT NULL , nickname VARCHAR(100), sex INTEGER, province VARCHAR(20), city VARCHAR(20), country VARCHAR(20), headimgurl VARCHAR(100), privilege VARCHAR(100), unionid VARCHAR(100), access_time DATETIME) CHARACTER SET = utf8;