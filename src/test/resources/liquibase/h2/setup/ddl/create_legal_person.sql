CREATE TABLE tbl_legal_person (
    id BIGINT PRIMARY KEY,
    firm_name VARCHAR(255) NOT NULL,
    founding_date DATE,
    -- Foreign key reference to tbl_buyer, since LegalPerson extends Buyer
    CONSTRAINT fk_buyer FOREIGN KEY (id) REFERENCES tbl_buyer(id)
);