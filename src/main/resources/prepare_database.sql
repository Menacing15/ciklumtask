CREATE DATABASE ciklumtask;
CREATE USER 'ciklumuser'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON ciklumtask.* TO 'ciklumuser'@'localhost';
CREATE TABLE ciklumtask.orders(id INT, user_id INT, status VARCHAR(20), created_at DATE, PRIMARY KEY(id));
CREATE TABLE ciklumtask.products(id INT, name VARCHAR(255), price INT, 
status ENUM('out_of_stock', 'in_stock', 'running_low'), created_at DATE, PRIMARY KEY(id));
CREATE TABLE ciklumtask.order_items(order_id INT, product_id INT, quantity INT);

