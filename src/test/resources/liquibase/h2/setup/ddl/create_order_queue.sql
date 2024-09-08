CREATE TABLE tbl_order_queue (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    xml_data CLOB NOT NULL,
    created_at DATE NOT NULL,
    processed BOOLEAN NOT NULL,
    supplier_id BIGINT,
    CONSTRAINT fk1_supplier
        FOREIGN KEY (supplier_id)
        REFERENCES tbl_partner(id)
);
