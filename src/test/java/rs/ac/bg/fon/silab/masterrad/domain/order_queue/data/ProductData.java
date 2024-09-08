package rs.ac.bg.fon.silab.masterrad.domain.order_queue.data;

import rs.ac.bg.fon.silab.masterrad.domain.enumeration.ProductType;
import rs.ac.bg.fon.silab.masterrad.domain.partner.Partner;
import rs.ac.bg.fon.silab.masterrad.domain.product.Product;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductData {
    Partner WELL_FORMED_SUPPLIER_1 = new Partner(
            null,
            "exampleAddress",
            "examplePartnerName");
    Product WELL_FORMED_PRODUCT_1 = new Product(
            1L,
            "exampleProductName",
            1.5,
            false,
            100,
            10,
            20,
            ProductType.Art,
            new BigDecimal("299.99"),
            WELL_FORMED_SUPPLIER_1,
            UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));

}
