use idrivedb;

create table product_t(
product_id int AUTO_INCREMENT PRIMARY KEY,
name      varchar(255),
description varchar(255),
price    decimal(8,3),
amount   int
);
