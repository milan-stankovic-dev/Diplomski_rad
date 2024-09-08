CREATE TABLE tbl_natural_person (
    id BIGINT PRIMARY KEY,
    buyer_name VARCHAR(255) NOT NULL,
    buyer_last_name VARCHAR(255) NOT NULL,
    CONSTRAINT fk_natural_person_buyer FOREIGN KEY (id) REFERENCES tbl_buyer(id)
);