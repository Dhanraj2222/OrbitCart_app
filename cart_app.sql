
CREATE DATABASE cart_app;   //Create a New Database


USE cart_app;       //Use for Implementation




// Create the Table with Our Parameters

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    maxQty INT NOT NULL,
    image_url VARCHAR(500) NOT NULL
);                                     




// Insert Raw Data For the Implimentation.

INSERT INTO products (name, price, maxQty, image_url) VALUES
('Cow Milk (1L)', 100.00, 5, 'https://example.com/images/cow_milk_1l.png'),
('A2 Cow Milk (1L)', 180.00, 10, 'https://example.com/images/a2_cow_milk_1l.png');


