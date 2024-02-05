DELIMITER $$

create trigger generate_invoice_trigger
after update on tbl_product
for each row
begin
    declare id_1_thresh int;
    declare id_3_thresh int;
    declare invoice_cond_1 boolean;
    declare invoice_cond_2 boolean;

    set id_1_thresh = 10;
    set id_3_thresh = 15;

    if new.id = 1 and new.amount <= id_1_thresh then
        set invoice_cond_1 = true;
    end if;

    if new.id = 3 and new.amount <= id_3_thresh then
        set invoice_cond_2 = true;
    end if;

    if invoice_cond_1 = true and invoice_cond_2 = true
        insert into order_queue (xml_data)
        values (generate_order_xml(1, 3));
    end if;
end;

DELIMITER ;