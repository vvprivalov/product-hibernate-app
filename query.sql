BEGIN;
CREATE TABLE IF NOT EXISTS products ( id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(45) NOT NULL, price INT NOT NULL);
INSERT INTO products (title, price) VALUES ('Молоко', 14), ('Кефир', 21), ('Сметана', 32), ('Сыр', 64), ('Творог', 29);
COMMIT;