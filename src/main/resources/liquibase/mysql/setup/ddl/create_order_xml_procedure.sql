create procedure generate_order_xml(IN prod_1_id int,
                                    IN prod_2_id int)
begin
    declare xml_data XML;

    select XMLElement(
    'Invoice'
    XMLAttributes(
    ),
    XMLForest(
    )
    ) into xml_data;

    insert into order_queue(xml_data)
    values (xml_data);
end;