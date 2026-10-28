INSERT INTO products (name, price, stock)
SELECT 'Wireless Mouse', 599.00, 20
WHERE NOT EXISTS (SELECT 1 FROM products WHERE name = 'Wireless Mouse');

INSERT INTO products (name, price, stock)
SELECT 'USB Keyboard', 899.00, 15
WHERE NOT EXISTS (SELECT 1 FROM products WHERE name = 'USB Keyboard');

INSERT INTO products (name, price, stock)
SELECT 'Headphones', 1499.00, 10
WHERE NOT EXISTS (SELECT 1 FROM products WHERE name = 'Headphones');
