create table if not exists tbl_order_queue (
    id SERIAL not null primary key,
    xml_data XML not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);