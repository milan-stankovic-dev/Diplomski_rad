CREATE TABLE tbl_bill_of_lading_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    amount_sold INT CHECK (amount_sold >= 1),
    product_id BIGINT,
    CONSTRAINT fk_product_bill_of_lading FOREIGN KEY (product_id) REFERENCES tbl_product(id)
);
