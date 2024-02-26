create or replace function get_order_supplier_ids()
returns SETOF INT as $$
declare
    supplier_id_record RECORD;
begin
    FOR supplier_id_record IN
        SELECT DISTINCT p.supplier_id
        FROM tbl_product p
        WHERE p.current_stock <= p.minimal_stock
        GROUP BY p.supplier_id
        HAVING COUNT(*) >= 2
    LOOP
        IF is_processed(supplier_id_record.supplier_id) THEN
                    RETURN NEXT supplier_id_record.supplier_id;
                END IF;
    END LOOP;

    RETURN;
end;
$$ language plpgsql;