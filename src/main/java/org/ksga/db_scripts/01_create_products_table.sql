CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(250),
    unit_price DOUBLE PRECISION,
    quantity INT,
    imported_date DATE DEFAULT CURRENT_DATE
);
