create table if not exists tbl_xml_schema (
    id serial not null primary key,
    xml_schema XML not null
);