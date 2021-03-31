CREATE DATABASE  idrivedb;

use idrivedb;

/*the owner of the table is catalogservice*/
CREATE TABLE product_t (
  product_id int NOT NULL AUTO_INCREMENT,
  amount int NOT NULL,
  description varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  price int NOT NULL,
  PRIMARY KEY (product_id)
) ;

/*the owner of the table is orderservice*/
CREATE TABLE order_t (
  order_id int NOT NULL AUTO_INCREMENT,
  address varchar(255) DEFAULT NULL,
  costumer_name varchar(255) DEFAULT NULL,
  created_at datetime(6) DEFAULT NULL,
  deliver_date datetime(6) DEFAULT NULL,
  shipped bit(1) DEFAULT NULL,
  total_price int DEFAULT NULL,
  PRIMARY KEY (order_id)
); 

/*the owner of the table is packingservice*/
CREATE TABLE packing_t (
  packing_id int NOT NULL AUTO_INCREMENT,
  code varchar(255) NOT NULL,
  comment varchar(255) NOT NULL,
  created_at datetime(6) NOT NULL,
  deliver_date datetime(6) NOT NULL,
  order_id int DEFAULT NULL,
  PRIMARY KEY (packing_id),
  KEY forein_key_order_t (order_id),
  CONSTRAINT forein_key_order_t FOREIGN KEY (order_id) REFERENCES order_t (order_id)
); 
