create or replace function generate_invoice() RETURNS XML as $$
declare
    xml_data XML;
    sum_total numeric(50,2);
begin
    select sum(price * order_amount)
    from tbl_product
    where current_stock <= minimal_stock
    into sum_total;

    select XMLELEMENT(
        name "invoice",
        XMLELEMENT (
        name "issue_date",
            CURRENT_DATE),
        XMLELEMENT (
        name "due_date",
            CURRENT_DATE + INTERVAL '3 months'),
        XMLELEMENT(
        name "total_cost",
            sum_total),
        XMLELEMENT (
        name "Invoice_items",
        XMLAGG(
            XMLELEMENT(
                name "item",
                XMLFOREST(order_amount, product_name)
               )
           )
       )
    ) into xml_data
    from tbl_product
    where current_stock <= order_amount;

    return xml_data;
end;
$$
language plpgsql;