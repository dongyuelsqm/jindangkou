/* 2017-1-16 create */
insert into products (name, department_id, unit_price, description, pictures, videos, minimum, postal, code, input_date) values('jeans', 1, 1.11, 'good','pic', 'videos', 10, '1', 'code', '2016-12-22 00:00:00');
insert into sizes (name) values('avg');
insert into colors (name) values ('red');
insert into product_quantity (product_id, color, size, number) values(1,1,1, 10);
insert into departments (name) values ('treasures');
insert into addresses(name, open_id, phone, province, city, district, detail) values ('zhangsan', 'ssss', '15111111', 'zhejiang', 'hangzhou', 'xihu', 'xihu');
insert into labels(title) values ("hot");
/*insert into product_label_links (product_id, label) values (1, 1);  --label改成label_id */
/*insert into sub_orders (product_id, product_id, order_id, color_id, size_id, number) values (1, 1, 1, 1, 1, 1);--多了一个字段 */
insert into notifications (title, created_at) values ("打折打折打折", '2016-12-22 00:00:00');

/* 2017-1-16 update */
alter table product_label_links add column label_id int;
alter table product_label_links drop column label;
insert into product_label_links (product_id, label_id) values (1, 1);
insert into sub_orders (product_id, order_id, color_id, size_id, number) values (1, 1, 1, 1, 1);
INSERT INTO customers(open_id, nickname, sex, province, city, country, headimgurl, privilege , unionid, access_time)VALUES (1, 'nickname', '1', 'hebei', 'cangzhou', 'China', '2', 'd', 'ddd', '2016-1-1 00:00:00');

/* 2017-1-22 update */
insert into orders (discount, open_id, address_id, deal_date, ship_id, state, express_number, method_price, actual_price) values (1.1, 1, 1, '2012-1-1', 'd', 1, 'dd', 1.1, 1.1);
