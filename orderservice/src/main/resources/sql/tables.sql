use idrivedb;

create table product_t(
product_id int AUTO_INCREMENT PRIMARY KEY,
name      varchar(255),
description varchar(255),
price    decimal(8,3),
amount   int
);
create table order_t(
order_id int AUTO_INCREMENT PRIMARY KEY,
total_price  decimal(8,3),
create_at    date,
deliver_date date
);