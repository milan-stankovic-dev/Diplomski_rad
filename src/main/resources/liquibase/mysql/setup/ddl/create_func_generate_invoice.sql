CREATE OR REPLACE FUNCTION generate_invoice(product_supplier_id INT)
RETURNS XML AS $$
DECLARE
    xml_data XML;
    sum_total NUMERIC(50,2);
--    invoice_schema XML;
    supplier_name VARCHAR(255);
BEGIN
    SELECT SUM(price * order_amount)
    FROM tbl_product
    WHERE current_stock <= minimal_stock
        AND supplier_id = product_supplier_id
    INTO sum_total;

    SELECT business_partner_name
    FROM tbl_partner
    WHERE id = product_supplier_id
    INTO supplier_name;

    SELECT XMLELEMENT(
        NAME "invoice",
        XMLELEMENT (
        NAME "issue_date",
            CURRENT_DATE),
        XMLELEMENT (
        NAME "due_date",
            CURRENT_DATE + INTERVAL '3 months'),
        XMLELEMENT(
        NAME "ordering_from",
            supplier_name
        ),
        XMLELEMENT(
        NAME "total_cost",
            sum_total),
        XMLELEMENT (
        NAME "Invoice_items",
        XMLAGG(
            XMLELEMENT(
                NAME "item",
                XMLFOREST(order_amount, product_name, code)
               )
           )
       )
    ) INTO xml_data
    FROM tbl_product
    WHERE current_stock <= order_amount
    AND supplier_id = product_supplier_id;

--    SELECT xml_schema
--    FROM diplomski_rad.tbl_xml_schema
--    WHERE id = 1
--    INTO invoice_schema;

--    IF xml_validate(xml_data, invoice_schema) THEN
        RETURN xml_data;
--    ELSE
--        RAISE EXCEPTION 'Generated XML does not conform to XML schema';
--    END IF;
END;
$$ LANGUAGE plpgsql;
