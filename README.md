# nocturne-music
Nocturne Music Store is a web application for browsing and purchasing musical instruments and accessories. This project is built using Java with Spring Boot, Thymeleaf, and MySQL.

## Features

- Browse products by category
- Search for products
- View product details
- Add products to shopping cart
- Checkout and mock purchase process
- User registration and authentication
- Admin panel for managing products, categories, users, and deals

## MySQL Setup

1. Install MySQL on your local machine if you haven't already done so.
2. Open a terminal and log in to MySQL using the command: `mysql -u root -p`.
3. Create a new database using the command: `CREATE DATABASE nocturne_db;`.
4. Verify that the database was created using the command: `SHOW DATABASES;`. You should see "nocturne_db" in the list of databases.
5. Use the database by using the command: `USE nocturne_db`
6. When using `nocturne_db` run the following script to setup all the tables
```mysql
-- Create 'user' table
CREATE TABLE user
(
    id       BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    email    VARCHAR(255) UNIQUE NOT NULL,
    enabled  BOOLEAN             NOT NULL DEFAULT 1,
    is_admin BOOLEAN             NOT NULL DEFAULT 0
);

-- Create 'category' table
CREATE TABLE category
(
    id   BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

-- Create 'product' table
CREATE TABLE product
(
    id          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    description TEXT,
    price       DECIMAL(10, 2) NOT NULL,
    image_url   VARCHAR(255),
    category_id BIGINT UNSIGNED,
    FOREIGN KEY (category_id) REFERENCES category (id)
);

-- Create 'shopping_cart' table
CREATE TABLE shopping_cart
(
    id      BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT UNSIGNED UNIQUE,
    FOREIGN KEY (user_id) REFERENCES user (id)
);

-- Create 'shopping_cart_item' table
CREATE TABLE shopping_cart_item
(
    shopping_cart_id BIGINT UNSIGNED,
    product_id       BIGINT UNSIGNED,
    quantity         INT UNSIGNED NOT NULL,
    PRIMARY KEY (shopping_cart_id, product_id),
    FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
);
```
7. (Optional) Run the following script to fill with mock data:
```mysql
-- Insert mock data into 'user' table
INSERT INTO user (username, password, email, enabled, is_admin)
VALUES ('paul', '123456', 'paul@beatles.com', 1, 0),
       ('john', '123456', 'john@beatles.com', 1, 0),
       ('admin', '123456', 'admin@gmail.com', 1, 1);

-- Insert mock data into 'category' table
INSERT INTO category (name)
VALUES ('Guitars'), ('Keyboards'), ('Drums'), ('Accessories');

-- Insert mock data into 'product' table
INSERT INTO product (name, description, price, image_url, category_id)
VALUES ('Fender Stratocaster', 'Classic electric guitar', 899.99, 'fender_stratocaster.jpg', 1),
       ('Gibson Les Paul', 'Iconic electric guitar', 1199.99, 'gibson_les_paul.jpg', 1),
       ('Yamaha P-125', '88-Key digital piano', 649.99, 'yamaha_p125.jpg', 2),
       ('Korg Minilogue', 'Analog synthesizer', 499.99, 'korg_minilogue.jpg', 2),
       ('Pearl Roadshow', '5-Piece drum set', 479.99, 'pearl_roadshow.jpg', 3),
       ('Tama Imperialstar', '6-Piece drum set', 699.99, 'tama_imperialstar.jpg', 3),
       ('Guitar Strap', 'Adjustable guitar strap', 14.99, 'guitar_strap.jpg', 4),
       ('Drumsticks', 'Pair of wooden drumsticks', 7.99, 'drumsticks.jpg', 4);

-- Insert mock data into 'shopping_cart' table
INSERT INTO shopping_cart (user_id)
VALUES (1), (2);

-- Insert mock data into 'shopping_cart_item' table
INSERT INTO shopping_cart_item (shopping_cart_id, product_id, quantity)
VALUES (1, 1, 1),
       (1, 3, 1),
       (2, 4, 1),
       (2, 7, 3);
```