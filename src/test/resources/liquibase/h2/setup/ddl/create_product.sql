CREATE TABLE tbl_product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    weight DOUBLE,
    fragile BOOLEAN,
    current_stock INT CHECK (current_stock >= 0),
    minimal_stock INT CHECK (minimal_stock >= 0),
    order_amount INT CHECK (order_amount >= 1),
    type VARCHAR(255),
    price DECIMAL(19, 2) CHECK (price > 0 AND price <= 1000000),
    supplier_id BIGINT,
    code UUID NOT NULL UNIQUE,
    CONSTRAINT fk_supplier FOREIGN KEY (supplier_id) REFERENCES tbl_partner(id)
);
