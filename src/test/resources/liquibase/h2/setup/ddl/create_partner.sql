CREATE TABLE tbl_partner (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    business_partner_address VARCHAR(255),
    business_partner_name VARCHAR(255) NOT NULL,
    UNIQUE (business_partner_name)
);
