CREATE TABLE tbl_report (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    report_date DATE CHECK (report_date <= CURRENT_DATE),
    total_capacity DOUBLE CHECK (total_capacity >= 0 AND total_capacity <= 100)
);
