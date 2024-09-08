CREATE TABLE tbl_bill_of_lading (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dead_line DATE CHECK (dead_line >= CURRENT_DATE),
    issue_date DATE CHECK (issue_date <= CURRENT_DATE),
    total_cost DECIMAL(19, 2) CHECK (total_cost > 0 AND total_cost <= 1000000),
    buyer_id BIGINT,
    CONSTRAINT fk1_buyer FOREIGN KEY (buyer_id) REFERENCES tbl_buyer(id)
);
