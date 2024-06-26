-- Add Products
INSERT INTO Product (name, description, price, stock)
VALUES ('Apple', 'Fresh Red Apples', 0.50, 100),
       ('Orange', 'Fresh Valencia Oranges', 0.60, 75),
       ('Strawberry', 'Fresh Strawberries', 2.00, 50),
       ('Blueberry', 'Fresh Blueberries', 3.00, 40),
       ('Mango', 'Fresh Mango', 1.00, 30),
       ('Watermelon', 'Fresh Watermelon', 5.00, 20),
       ('Peach', 'Fresh Peaches', 1.50, 15),
       ('Pineapple', 'Organic Pineapple', 2.50, 25),
       ('Banana', 'Fresh Bananas', 0.30, 10);

-- Add Customers
INSERT INTO Customer (firstName, lastName, email, password)
VALUES ('John', 'Doe', 'john@email.com', 'password123'),
       ('Jane', 'Smith', 'jane@email.com', 'password123'),
       ('test', 'test2', 'a@email.com', 'a');

-- Add items to the Basket
-- John Doe has 10 Apples and 20 Oranges in his basket
-- Jane Smith has 30 Strawberries in her basket
INSERT INTO Basket (customer_id, product_id, quantity)
VALUES ((SELECT id FROM Customer WHERE email = 'john@email.com'), (SELECT id FROM Product WHERE name = 'Apple'), 10),
       ((SELECT id FROM Customer WHERE email = 'john@email.com'), (SELECT id FROM Product WHERE name = 'Orange'), 20),
       ((SELECT id FROM Customer WHERE email = 'jane@email.com'), (SELECT id FROM Product WHERE name = 'Strawberry'), 30);