package rs.ac.bg.fon.silab.diplomskirad.domain.bill_of_lading.SOInsert;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.silab.diplomskirad.domain.partner.product.Product;
import rs.ac.bg.fon.silab.diplomskirad.domain.bill_of_lading.BillOfLading;
import rs.ac.bg.fon.silab.diplomskirad.dto.BillOfLadingDTO;
import rs.ac.bg.fon.silab.diplomskirad.mapper.BillOfLadingMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.BillOfLadingRepository;
import rs.ac.bg.fon.silab.diplomskirad.repository.ProductRepository;
import rs.ac.bg.fon.silab.diplomskirad.validator.BusinessDocumentValidator;

@Component
@RequiredArgsConstructor
public class InsertBillRequestHandler
        implements RequestHandler<BillOfLadingDTO,
                                  BillOfLadingDTO> {

    private final BillOfLadingMapper billOfLadingMapper;
    private final BillOfLadingRepository repository;
    private final ProductRepository productRepository;
    private final BusinessDocumentValidator<BillOfLading> validator;
    private final Mediator mediator;

    @Transactional
    private void decreaseItemStock(BillOfLading bill) {
        val items = bill.getItems();

        items.forEach(item -> {
            final Product foundProduct =
                    productRepository.findById(item.getProduct().getId())
                                    .orElseThrow(()->new EntityNotFoundException
                                            ("You are trying to sell a non-existing item."));
            foundProduct.decreaseStockBy(item.getAmountSold());
            productRepository.save(foundProduct);
        });
    }

    @Transactional
    private BillOfLadingDTO insertBillOfLading(BillOfLadingDTO billDTO) {
        final BillOfLading bill = billOfLadingMapper
                .dTOtoEntity(billDTO);
        validator.validateDocument(bill);
        decreaseItemStock(bill);
        val savedBill = repository.save(bill);

        return billOfLadingMapper.entityToDTO(savedBill);
    }

    @Override
    public BillOfLadingDTO handle(@NotNull BillOfLadingDTO
                                              billOfLadingDTO) {
        final BillOfLadingDTO savedBill =
                insertBillOfLading(billOfLadingDTO);
        this.mediator.emit(savedBill);

        return savedBill;
    }


}
