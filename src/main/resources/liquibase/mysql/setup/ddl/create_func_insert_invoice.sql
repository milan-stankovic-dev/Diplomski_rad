CREATE OR REPLACE FUNCTION insert_invoice(invoice XML, supplier INT)
RETURNS VOID AS $$
BEGIN
    INSERT INTO diplomski_rad.tbl_order_queue(xml_data, processed, schema_id, supplier_id)
    VALUES (invoice, false, 1, supplier);
END;
$$ LANGUAGE plpgsql;
