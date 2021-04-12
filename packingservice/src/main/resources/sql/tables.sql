use idrivedb;

create table packing_t(
packing_id int AUTO_INCREMENT PRIMARY KEY,
code         varchar(255),
comment      varchar(255),
create_at    date,
deliver_date date
);
create table order_t(
order_id int AUTO_INCREMENT PRIMARY KEY,
total_price  decimal(8,3),
create_at    date,
deliver_date date
);
