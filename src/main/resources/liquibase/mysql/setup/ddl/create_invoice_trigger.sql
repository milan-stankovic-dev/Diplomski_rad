create trigger invoice_generation_trigger
after update on tbl_product
for each statement
execute function chk_invoice_gen();