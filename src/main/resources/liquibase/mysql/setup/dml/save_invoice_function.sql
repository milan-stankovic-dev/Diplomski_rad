CREATE OR REPLACE FUNCTION insert_invoice(invoice XML)
RETURNS VOID AS $$
BEGIN
    INSERT INTO diplomski_rad.tbl_order_queue(xml_data)
    VALUES (invoice);
END;
$$ LANGUAGE plpgsql;
