package rs.ac.bg.fon.silab.diplomskirad.domain.abstraction;

import rs.ac.bg.fon.silab.diplomskirad.domain.product.Product;

public interface BusinessDocumentItem {
    Product getProduct();
    int getAmountTransacted();
}
