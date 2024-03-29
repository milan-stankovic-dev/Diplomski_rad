package rs.ac.bg.fon.silab.diplomskirad.domain.abstraction;

import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.silab.diplomskirad.domain.product.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface BusinessDocument<ITEM_TYPE
        extends BusinessDocumentItem> {
    Set<? extends ITEM_TYPE> getItems();
    BigDecimal getTotalValue();

}
