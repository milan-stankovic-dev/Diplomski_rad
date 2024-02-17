CREATE OR REPLACE FUNCTION chk_invoice_gen()
RETURNS TRIGGER AS $$
DECLARE
    xml_result XML;
    condition_count INT;
BEGIN
    SELECT COUNT(*)
    INTO condition_count
    FROM tbl_product
    WHERE current_stock <= minimal_stock;

    IF condition_count >= 2 THEN
        SELECT diplomski_rad.generate_invoice()
        INTO xml_result;
        PERFORM diplomski_rad.insert_invoice(xml_result);
    END IF;
    
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;
