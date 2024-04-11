create table if not exists tbl_order_queue (
    id SERIAL not null primary key,
    xml_data XML not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    processed boolean,
--    schema_id SERIAL,
--     foreign key(schema_id) references tbl_xml_schema(id)
--    on delete restrict on update cascade,
    supplier_id SERIAL,
     foreign key(supplier_id) references tbl_partner(id)
    on delete restrict on update cascade
);