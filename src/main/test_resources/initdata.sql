/* 2017- - create */
create table products(id int primary key AUTO_INCREMENT, name varchar(100) not null, department_id int not null, unit_price float not null, description varchar(500), pictures varchar(200), videos varchar(200),  minimum int, postal varchar(10), code varchar(20), input_date datetime) character set = utf8;
create table product_quantity(id int primary key AUTO_INCREMENT, product_id int, color varchar(10), size varchar(10), number int) character set = utf8;
create table product_label_links(id int AUTO_INCREMENT primary key, product_id int, label_id int) character set = utf8;
create table orders(id int primary key AUTO_INCREMENT, discount float, open_id int not null, address_id int not null, deal_date datetime, ship_id varchar(200), state int, express_number varchar(100), method_price FLOAT , actural_price FLOAT) character set = utf8;//修改了数据表的列名，并去除了不需要的列名。
create table sub_orders(id int primary key AUTO_INCREMENT, product_id int, order_id int, color_id varchar(10), size_id varchar(10), number int) character set = utf8;
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

/* 2017-1- update */