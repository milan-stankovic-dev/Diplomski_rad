CREATE TABLE tbl_report_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_capacity DOUBLE CHECK (product_capacity >= 0 AND product_capacity <= 100),
    product_id BIGINT,
    total_available_capacity INT DEFAULT 200,
    CONSTRAINT fk1_product FOREIGN KEY (product_id) REFERENCES tbl_product(id)
);
