package rs.ac.bg.fon.silab.masterrad.domain.abstraction;

import rs.ac.bg.fon.silab.masterrad.domain.product.Product;

public interface BusinessDocumentItem {
    Product getProduct();
    int getAmountTransacted();
}
