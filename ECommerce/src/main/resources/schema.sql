CREATE DATABASE IF NOT EXISTS onlineStore;
USE onlineStore;

CREATE TABLE Product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT,
                         price DECIMAL(10, 2) NOT NULL,
                         stock INT NOT NULL
);

CREATE TABLE Customer (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          firstName VARCHAR(255) NOT NULL,
                          lastName VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          password VARCHAR(255) NOT NULL
);

CREATE TABLE Basket (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        customer_id BIGINT NOT NULL,
                        product_id BIGINT NOT NULL,
                        quantity INT NOT NULL,
                        FOREIGN KEY (customer_id) REFERENCES Customer(id),
                        FOREIGN KEY (product_id) REFERENCES Product(id)
);