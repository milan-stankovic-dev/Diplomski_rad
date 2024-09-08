CREATE TABLE tbl_goods_received_note (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dead_line DATE CHECK (dead_line >= CURRENT_DATE),
    issue_date DATE CHECK (issue_date <= CURRENT_DATE),
    total_cost DECIMAL(19, 2) CHECK (total_cost > 0),
    partner_id BIGINT,
    CONSTRAINT fk_partner FOREIGN KEY (partner_id) REFERENCES tbl_partner(id)
);