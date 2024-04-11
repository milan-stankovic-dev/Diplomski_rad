package rs.ac.bg.fon.silab.diplomskirad.validator;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.abstraction.BusinessDocument;
import rs.ac.bg.fon.silab.diplomskirad.domain.abstraction.BusinessDocumentItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Component
public class BusinessDocumentValidator<DOCUMENT extends BusinessDocument<? extends BusinessDocumentItem>> {

    private void documentNullValidator(DOCUMENT document) {
        if(document == null) {
            throw new IllegalArgumentException("Document must not be empty.");
        }
    }
    private void documentItemValidator(Set<? extends BusinessDocumentItem> items) {
        if(items == null || items.isEmpty()){
            throw new IllegalStateException("Your document has" +
                    " no items.");
        }
    }
    private void documentItemNullValidator(BusinessDocumentItem item) {
        if(item == null) {
            throw new IllegalArgumentException("Your item may not not be null.");
        }
    }

    private void allDocumentItemsValidator(Set<? extends BusinessDocumentItem> items){
        documentItemValidator(items);
        items.forEach(this::documentItemNullValidator);
        items.forEach(this::documentItemNullProductValidator);
    }


    public void documentItemNullProductValidator(BusinessDocumentItem item) {
        documentItemNullValidator(item);
        if(item.getProduct() == null){
            throw new NullPointerException("Your item must refer" +
                    "to a product.");
        }
    }

    private void documentTotalCostValidator(DOCUMENT document) {
        BigDecimal checkSum = BigDecimal.valueOf(0);
        final BigDecimal billSum = document.getTotalValue();

        for(BusinessDocumentItem item : document.getItems()) {
            //here we check if the total sum is what it is represented as
            //in the field of bill object by manually calculating the total
            //cost of our sale to buyer.
            checkSum = checkSum.add(item.getProduct().getPrice()
                    .multiply(BigDecimal.valueOf(item.getAmountTransacted())));
        }
        if(!checkSum.equals(billSum)) {
            throw new IllegalStateException("Your document's total does " +
                    "not reflect the true state of the document.");
        }
    }

    public void validateDocument(DOCUMENT document) {
        documentNullValidator(document);
        allDocumentItemsValidator(document.getItems());
        documentTotalCostValidator(document);
    }

}
