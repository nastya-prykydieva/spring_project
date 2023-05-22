CREATE TABLE IF NOT EXISTS categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    picture BYTEA
);

CREATE TABLE IF NOT EXISTS customers (
    id VARCHAR(255) PRIMARY KEY,
    company_name VARCHAR(255),
    contact_name VARCHAR(255),
    contact_title VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    region VARCHAR(255),
    postal_code VARCHAR(255),
    country VARCHAR(255),
    phone VARCHAR(255),
    fax VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS employees (
    id SERIAL PRIMARY KEY,
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    title VARCHAR(255),
    title_of_courtesy VARCHAR(255),
    birth_date TIMESTAMP,
    hire_date TIMESTAMP,
    address VARCHAR(255),
    city VARCHAR(255),
    region VARCHAR(255),
    postal_code VARCHAR(255),
    country VARCHAR(255),
    home_phone VARCHAR(255),
    extension VARCHAR(255),
    photo BYTEA,
    notes TEXT,
    reports_to INTEGER,
    photo_path VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    customer_id VARCHAR(255) NOT NULL,
    employee_id BIGINT NOT NULL,
    shipper_id BIGINT,
    order_date TIMESTAMP,
    required_date TIMESTAMP,
    shipped_date TIMESTAMP,
    ship_via BIGINT,
    freight NUMERIC(10, 2),
    ship_name VARCHAR(255),
    ship_address VARCHAR(255),
    ship_city VARCHAR(255),
    ship_state VARCHAR(255),
    ship_region VARCHAR(255),
    ship_postal_code VARCHAR(255),
    ship_country VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS order_details (
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    unit_price NUMERIC(10, 2),
    quantity INTEGER,
    discount NUMERIC(10, 2),
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    supplier_id BIGINT,
    category_id BIGINT,
    quantity_per_unit VARCHAR(255),
    unit_price NUMERIC(10, 2),
    units_in_stock INTEGER,
    units_on_order INTEGER,
    reorder_level INTEGER,
    discontinued BOOLEAN,
    FOREIGN KEY (supplier_id) REFERENCES suppliers (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE IF NOT EXISTS shippers (
    id SERIAL PRIMARY KEY,
    company_name VARCHAR(255) NOT NULL,
    phone VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS suppliers (
    id SERIAL PRIMARY KEY,
    company_name VARCHAR(255) NOT NULL,
    contact_name VARCHAR(255),
    contact_title VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    region VARCHAR(255),
    postal_code VARCHAR(255),
    country VARCHAR(255),
    phone VARCHAR(255),
    fax VARCHAR(255),
    home_page VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    CONSTRAINT username_unique UNIQUE (username),
    CONSTRAINT email_unique UNIQUE (email)
);