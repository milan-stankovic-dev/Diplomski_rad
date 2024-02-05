create table order_queue(
    id int auto_increment primary key,
    xml_data text
    created_at TIMESTAMP default CURRENT_TIMESTAMP
);