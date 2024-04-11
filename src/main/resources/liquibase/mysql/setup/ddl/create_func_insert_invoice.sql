CREATE OR REPLACE FUNCTION insert_invoice(invoice XML, supplier INT)
RETURNS VOID AS $$
BEGIN
    INSERT INTO tbl_order_queue(xml_data, processed, supplier_id)
    VALUES (invoice, false, supplier);
END;
$$ LANGUAGE plpgsql;
