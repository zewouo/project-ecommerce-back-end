CREATE DATABASE  idrivedb;

use idrivedb;


CREATE TABLE product_t (
  product_id int NOT NULL AUTO_INCREMENT,
  code_product varchar(255) NOT NULL,
  description varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  price int NOT NULL,
  quantity int NOT NULL,
  PRIMARY KEY (product_id)
);


CREATE TABLE order_t (
  order_id int NOT NULL AUTO_INCREMENT,
   address varchar(255) NOT NULL,
  costumer_name varchar(255) DEFAULT NULL,
  created_at datetime(6) DEFAULT NULL,
  deliver_date datetime(6) DEFAULT NULL,
  order_number varchar(255) NOT NULL,
  shipped bit(1) DEFAULT NULL,
  total_price int DEFAULT NULL,
  PRIMARY KEY (order_id)
);


CREATE TABLE packing_t (
  packing_id int NOT NULL AUTO_INCREMENT,
  code_packing varchar(255) NOT NULL,
  code varchar(255) NOT NULL,
  comment varchar(255) NOT NULL,
  created_at datetime(6) NOT NULL,
  deliver_date datetime(6) NOT NULL,
  order_id int DEFAULT NULL,
  PRIMARY KEY (packing_id),
  KEY forein_key_order_t (order_id),
  CONSTRAINT forein_key_order_t FOREIGN KEY (order_id) REFERENCES order_t (order_id)
);


CREATE TABLE order_product_t (
  order_number varchar(255) NOT NULL,
  quantity int NOT NULL,
  product_id int NOT NULL,
  order_id int NOT NULL,
  PRIMARY KEY (order_number,product_id),
  KEY forein_key_order_product_product_t (product_id),
  KEY forein_key_order_product_order_t (order_id),
  CONSTRAINT forein_key_order_product_order_t FOREIGN KEY (order_id) REFERENCES order_t (order_id),
  CONSTRAINT forein_key_order_product_product_t FOREIGN KEY (product_id) REFERENCES product_t (product_id)
);