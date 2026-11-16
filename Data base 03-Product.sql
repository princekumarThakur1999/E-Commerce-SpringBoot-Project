Data base table for Product API Application.

-- Drop and create schema
DROP SCHEMA IF EXISTS ashokit_ecomm CASCADE;

CREATE SCHEMA ashokit_ecomm;

-- Set search path (optional)
SET search_path TO ashokit_ecomm;

-- Create table: product_category
CREATE TABLE IF NOT EXISTS product_category_tbl (
  category_id BIGSERIAL PRIMARY KEY,
  category_name VARCHAR(255)
);

-- Create table: product
CREATE TABLE IF NOT EXISTS product_tbl (
  product_id BIGSERIAL PRIMARY KEY,
  title VARCHAR(255),
  name VARCHAR(255),
  description VARCHAR(255),
  unit_price NUMERIC(13,2),
  image_url VARCHAR(255),
  active BOOLEAN DEFAULT TRUE,
  units_in_stock INTEGER,
  date_created TIMESTAMP,
  last_updated TIMESTAMP,
  category_id BIGINT NOT NULL,
  CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES product_category (category_id)
);

-- Insert product categories
INSERT INTO product_category (category_name) VALUES
('Laptops'),
('Mobiles'),
('Shirts'),
('Beauty');

-- Insert products
INSERT INTO product (title, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) VALUES
('DELL-LAPTOP-1000', 'DELL - Laptop', 'Processor: Intel Core i5-1235U 12th Generation (up to 4.40 GHz, 12MB 10 Cores)',
'assets/images/products/laptops/dell-laptop-1000.png', TRUE, 100, 19.99, 1, CURRENT_TIMESTAMP),

('HP-LAPTOP-1001', 'HP - Laptop', 'Processor: Intel Core i5-1235U 12th Generation (up to 4.40 GHz, 12MB 10 Cores)',
'assets/images/products/laptops/hp-laptop-1001.png', TRUE, 100, 59.99, 1, CURRENT_TIMESTAMP),

('ACER-LAPTOP-1002', 'ACER - Laptop', 'Acer Aspire Lite 12th Gen Intel Core i5-1235U Thin and Light Laptop',
'assets/images/products/laptops/acer-laptop-1002.png', TRUE, 100, 49.99, 1, CURRENT_TIMESTAMP),

('LENOVO-LAPTOP-1003', 'Lenovo ThinkPad E14', 'Lenovo ThinkPad E14 AMD Ryzen 5',
'assets/images/products/laptops/lenovo-laptop-1003.png', TRUE, 100, 45.00, 1, CURRENT_TIMESTAMP),

('Apple-IPhone-1000', 'Apple-Iphone', 'Apple iPhone 13 (128GB) - Green',
'assets/images/products/mobiles/apple-mobile-1000.png', TRUE, 100, 59.00, 2, CURRENT_TIMESTAMP),

('RED-MI-1001', 'Redmi 13C', 'Redmi 13C (Stardust Black, 6GB RAM, 128GB Storage)',
'assets/images/products/mobiles/redmi-mobile-1001.png', TRUE, 100, 29.00, 2, CURRENT_TIMESTAMP),

('SAMSUNG-Galaxy-1002', 'Samsung Mobile', 'SAMSUNG Galaxy F14 5G 6GB RAM 128GB STORAGE',
'assets/images/products/mobiles/samsung-mobile-1002.png', TRUE, 100, 39.00, 2, CURRENT_TIMESTAMP),

('POCO C65', 'POCO C65', 'POCO C65 (Pastel Green 4GB RAM 128GB Storage)',
'assets/images/products/mobiles/poco-mobile-1003.png', TRUE, 100, 49.00, 2, CURRENT_TIMESTAMP),

('Mens Cotton Shirt', 'Mens Cotton Shirt', 'Amazon Brand - Symbol Mens Cotton Shirt',
'assets/images/products/clothes/shirt-1000.png', TRUE, 100, 5.00, 3, CURRENT_TIMESTAMP);

