DELIMITER $$

create procedure send_email(IN product_id INT)
BEGIN
    declare xml_data XML;

    select xmlconcat(
        xmlforest(
        '')
    )
END;
DELIMITER ;