CREATE OR REPLACE FUNCTION create_invoice_schema() RETURNS XML AS $$
BEGIN
    RETURN XMLSCHEMA(
        'xmlns="http://www.w3.org/2001/XMLSchema"',
        XMLFOREST(
            XMLElement(name "invoice",
                XMLElement(name "issue_date", XMLDECL),
                XMLElement(name "due_date", XMLDECL),
                XMLElement(name "total_cost", XMLDECL),
                XMLElement(name "Invoice_items", XMLDECL)
            )
        )
    );
END;
$$ LANGUAGE plpgsql;
