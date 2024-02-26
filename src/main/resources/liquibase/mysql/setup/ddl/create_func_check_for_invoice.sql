CREATE OR REPLACE FUNCTION chk_invoice_gen()
RETURNS TRIGGER AS $$
DECLARE
    xml_result XML;
    supplier_id INT;
BEGIN
    FOR supplier_id IN (SELECT * FROM get_order_supplier_ids())
    LOOP
        xml_result := diplomski_rad.generate_invoice(supplier_id);
        PERFORM diplomski_rad.insert_invoice(xml_result, supplier_id);
    END LOOP;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
