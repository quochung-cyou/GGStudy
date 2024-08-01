-- create the databases
CREATE DATABASE IF NOT EXISTS `learning`;
CREATE DATABASE IF NOT EXISTS `public`;
USE learning;

-- create the users for each database
CREATE USER 'admin'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON `learning`.* TO 'admin'@'%';
GRANT ALL PRIVILEGES ON `public`.* TO 'admin'@'%';
FLUSH PRIVILEGES;