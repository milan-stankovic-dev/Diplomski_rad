package rs.ac.bg.fon.silab.masterrad.domain.abstraction;

import java.math.BigDecimal;
import java.util.Set;

public interface BusinessDocument<ITEM_TYPE
        extends BusinessDocumentItem> {
    Set<? extends ITEM_TYPE> getItems();
    BigDecimal getTotalValue();

}
