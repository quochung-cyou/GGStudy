-- create the databases
CREATE DATABASE IF NOT EXISTS `learning`;
USE learning;

-- create the users for each database
CREATE USER 'admin'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON `learning`.* TO 'admin'@'%';
FLUSH PRIVILEGES;