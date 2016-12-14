create table products(id int primary key AUTO_INCREMENT, product_name VARCHAR(100) not null, department_id int not null, unit_price float not null, description VARCHAR(500), picture VARCHAR(100), minimum int, postal VARCHAR(10), code VARCHAR(20), deal_date datetime);
create table product_quantity(id int primary key AUTO_INCREMENT, product_id int, color VARCHAR(10), size VARCHAR(10)，number int)
create table employees(employee_id int primary key, first_name VARCHAR(100), last_name VARCHAR(100), title VARCHAR(100));
create table orders(order_id int primary key AUTO_INCREMENT, product_id int not null, product_number int not null, discount float, customer_id int not null, address_id int not null, deal_date datetime, ship_id VARCHAR(200), state int);
create table sub_order(id int primary key AUTO_INCREMENT, product_id int, order_id int, color VARCHAR(10), size VARCHAR(10)，number int)
create table addresses(address_id int primary key not null AUTO_INCREMENT, open_id int not null, province VARCHAR(100) not null, city VARCHAR(100) not null, district VARCHAR(100) not null, detail VARCHAR(100));
create table department(department_id int not null, AUTO_INCREMENT, name VARCHAR(100));
create table inventory(product_id int, color VARCHAR(100), number int)
create table admin(id int primary key AUTO_INCREMENT, username VARCHAR(100), password VARCHAR(100))
create table labels(id int primary key AUTO_INCREMENT, title VARCHAR(100))
create table product_label_links(id int AUTO_INCREMENT primary key, product_id int, label int)