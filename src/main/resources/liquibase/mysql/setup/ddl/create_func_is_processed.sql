create or replace function is_processed(supplier_id_param BIGINT)
returns boolean as $$
declare
    invoice_entries BOOLEAN;
    is_empty BOOLEAN;
begin
    select distinct processed
    from diplomski_rad.tbl_order_queue
    where supplier_id = supplier_id_param
    into invoice_entries;

    is_empty := NOT FOUND;

    IF is_empty OR (array_agg(invoice_entries))[1] = true THEN
         RETURN true;
    ELSE
         RETURN false;
    END IF;
end;
$$ language plpgsql;