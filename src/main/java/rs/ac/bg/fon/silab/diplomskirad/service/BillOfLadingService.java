package rs.ac.bg.fon.silab.diplomskirad.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.domain.bill_of_lading.BillOfLading;
import rs.ac.bg.fon.silab.diplomskirad.domain.bill_of_lading_item.BillOfLadingItem;
import rs.ac.bg.fon.silab.diplomskirad.domain.product.Product;
import rs.ac.bg.fon.silab.diplomskirad.dto.BillOfLadingDTO;
import rs.ac.bg.fon.silab.diplomskirad.mapper.BillOfLadingMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.BillOfLadingRepository;
import rs.ac.bg.fon.silab.diplomskirad.repository.ProductRepository;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class BillOfLadingService {
    private final BillOfLadingRepository repository;
    private final BillOfLadingMapper billOfLadingMapper;
    private final ProductRepository productRepository;

    public BillOfLadingDTO insertBillOfLading(BillOfLadingDTO billDTO) {
        var bill = billOfLadingMapper.dTOtoEntity(billDTO);
        billItemValidator(bill);
        billTotalCostValidator(bill);
        decreaseItemStock(bill);
        var savedBill = repository.save(bill);

        return billOfLadingMapper.entityToDTO(savedBill);
    }
    private void billItemValidator(BillOfLading bill){
        if(bill == null){
            throw new IllegalArgumentException("Bill must not be empty.");
        }
        var items = bill.getItems();
        if(items == null || items.size() == 0){
            throw new IllegalStateException("Your bill of lading has" +
                    " no items.");
        }
    }

    private void billTotalCostValidator(BillOfLading bill) {
        BigDecimal checkSum = BigDecimal.valueOf(0);
        final BigDecimal billSum = bill.getTotalCost();

        for(BillOfLadingItem item : bill.getItems()){
            if(item.getProduct() == null){
                throw new NullPointerException("Your item must refer" +
                        "to a product.");
            }
            //here we check if the total sum is what it is represented as
            //in the field of bill object by manually calculating the total
            //cost of our sale to buyer.
            checkSum = checkSum.add(item.getProduct().getPrice()
                    .multiply(BigDecimal.valueOf(item.getAmountSold())));
        }
        if(!checkSum.equals(billSum)) {
            throw new IllegalStateException("Your bills total does " +
                    "not reflect the true state of the document.");
        }
    }
    private void decreaseItemStock(BillOfLading bill) {
        var items = bill.getItems();
        for(BillOfLadingItem item : items){
            Product foundProduct =
                    fetchProductIfPossible(item.getProduct().getId());

            foundProduct.decreaseStockBy(item.getAmountSold());
        }
    }
    private Product fetchProductIfPossible(long id){
        var productFromDBopt = productRepository
                .findById(id);
        if(productFromDBopt.isEmpty()){
            throw new EntityNotFoundException("You are trying " +
                    "to sell a non-existing item.");
        }
        return productFromDBopt.get();
    }
}
