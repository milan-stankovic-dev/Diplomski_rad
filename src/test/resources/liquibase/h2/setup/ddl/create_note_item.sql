CREATE TABLE tbl_goods_received_note_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    amount_ordered INT CHECK (amount_ordered >= 1),
    product_id BIGINT,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES tbl_product(id)
);
