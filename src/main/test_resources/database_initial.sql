use jindangkou;
drop table products;
drop table product_quantity;
drop table orders;
drop table sub_orders;
drop table addresses;
/*drop table department;*/
drop table inventory;
drop table admin;
drop table labels;
drop table product_label_links;
drop table colors;
drop table sizes;
drop table departments;
drop table collections;
drop table shoping_cart;
drop table notifications;
drop table customers;
DROP TABLE unified_order;

create table products(id int primary key AUTO_INCREMENT, name varchar(100) not null, department_id int not null, unit_price float not null, description varchar(500), pictures varchar(200), videos varchar(200),  minimum int, postal varchar(10), code varchar(20), input_date datetime) character set = utf8;
create table product_quantity(id int primary key AUTO_INCREMENT, product_id int, color varchar(10), size varchar(10), number int) character set = utf8;
create table product_label_links(id int AUTO_INCREMENT primary key, product_id int, label_id int) character set = utf8;
create table orders(id bigint primary key, discount float, open_id VARCHAR(50) not null, address_id int not null, deal_date datetime, ship_id varchar(200), state int, express_number varchar(100), method_price FLOAT , actual_price FLOAT, weixin_order_id INT) character set = utf8;
create table sub_orders(id int primary key AUTO_INCREMENT, product_id int, order_id bigint, color_id varchar(10), size_id varchar(10), number int) character set = utf8;
create table addresses(id int primary key not null AUTO_INCREMENT, open_id varchar(100) not null, name varchar(100), phone varchar(100), province varchar(100) not null, city varchar(100) not null, district varchar(100) not null, detail varchar(100)) character set = utf8;
create table inventory(id int PRIMARY KEY AUTO_INCREMENT,product_id int, color varchar(100), number int) character set = utf8;
create table admin(id int primary key AUTO_INCREMENT, username varchar(100), password varchar(100)) character set = utf8;
create table labels(id int primary key AUTO_INCREMENT, title varchar(100)) character set = utf8;
create table colors(id int primary key AUTO_INCREMENT, name varchar(100)) character set = utf8;
create table sizes(id int primary key AUTO_INCREMENT, name varchar(100)) character set = utf8;
create table departments(id int primary key AUTO_INCREMENT, name varchar(100)) character set = utf8;
create table collections(id int primary key AUTO_INCREMENT, product_id int, open_id int) character set = utf8;
create table shoping_cart(id int primary key AUTO_INCREMENT, product_id int, open_id int) character set = utf8;
create table notifications(id int primary key AUTO_INCREMENT, title varchar(100), created_at DATETIME) character set = utf8;
create table customers(id int primary key AUTO_INCREMENT, open_id varchar(100) NOT NULL , nickname varchar(100), sex integer, province varchar(20), city varchar(20), country varchar(20), headimgurl varchar(100), privilege varchar(100), unionid varchar(100), access_time DATETIME) character set = utf8;
CREATE TABLE unified_order(id int primary key AUTO_INCREMENT, app_id INT, mch_id INT, attach INT, body VARCHAR(1000), nonce_str VARCHAR(200), notify_url VARCHAR (200), openid VARCHAR(200), out_trade_no VARCHAR(200), spbill_create_ip VARCHAR(200), total_fee FLOAT, trade_type VARCHAR(200), sign VARCHAR(200), device_info VARCHAR(200));

insert into products (name, department_id, unit_price, description, pictures, videos, minimum, postal, code, input_date) values('jeans', 1, 1.11, 'good','pic', 'videos', 10, '1', 'code', '2016-12-22 00:00:00');
insert into sizes (name) values('avg');
insert into colors (name) values ('red');
insert into product_quantity (product_id, color, size, number) values(1,1,1, 10);
insert into departments (name) values ('treasures');
insert into addresses(name, open_id, phone, province, city, district, detail) values ('zhangsan', 'ssss', '15111111', 'zhejiang', 'hangzhou', 'xihu', 'xihu');
insert into labels(title) values ("hot");
insert into orders (id, discount, open_id, address_id, deal_date, ship_id, state, express_number, method_price, actual_price, weixin_order_id)
values (1, 1.1, 1, 1, '2012-1-1', 'd', 1, 'dd', 1.1, 1.1, 1);
insert into product_label_links (product_id, label_id) values (1, 1);
insert into sub_orders (product_id, order_id, color_id, size_id, number) values (1, 1, 1, 1, 1);
INSERT INTO customers(open_id, nickname, sex, province, city, country, headimgurl, privilege , unionid, access_time)VALUES (1, 'nickname', '1', 'hebei', 'cangzhou', 'China', '2', 'd', 'ddd', '2016-1-1 00:00:00');
INSERT INTO unified_order(id, app_id, mch_id, attach, body, nonce_str, notify_url, openid, out_trade_no, spbill_create_ip, total_fee, trade_type, sign, device_info)
VALUES (1, 1, 1, 1, 'body', 'nonce_str', 'notify_url', 'openid', 'out_trade_no', 'spbill_create_ip', 1.1, 'trade_type', 'sign', 'device_info');
