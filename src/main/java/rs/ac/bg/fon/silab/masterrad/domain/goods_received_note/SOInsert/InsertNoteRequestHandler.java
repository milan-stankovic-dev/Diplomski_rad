package rs.ac.bg.fon.silab.masterrad.domain.goods_received_note.SOInsert;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.silab.masterrad.domain.goods_received_note.GoodsReceivedNote;
import rs.ac.bg.fon.silab.masterrad.domain.product.Product;
import rs.ac.bg.fon.silab.masterrad.dto.GoodsReceivedNoteDTO;
import rs.ac.bg.fon.silab.masterrad.mapper.GoodsReceivedNoteMapper;
import rs.ac.bg.fon.silab.masterrad.repository.GoodsReceivedNoteRepository;
import rs.ac.bg.fon.silab.masterrad.repository.ProductRepository;
import rs.ac.bg.fon.silab.masterrad.validator.BusinessDocumentValidator;

@Component
@RequiredArgsConstructor
public class InsertNoteRequestHandler
        implements RequestHandler<GoodsReceivedNoteDTO, GoodsReceivedNoteDTO> {

    private final Mediator mediator;
    private final GoodsReceivedNoteRepository repository;
    private final GoodsReceivedNoteMapper noteMapper;
    private final ProductRepository productRepository;
    private final BusinessDocumentValidator<GoodsReceivedNote> validator;

    private GoodsReceivedNoteDTO insertNote(
            GoodsReceivedNoteDTO noteDTO) {

        val note = noteMapper.dTOtoEntity(noteDTO);
        validator.validateDocument(note);
        increaseItemStock(note);
        val savedNote = repository.save(note);

        return noteMapper.entityToDTO(savedNote);
    }


    @Transactional
    private void increaseItemStock(GoodsReceivedNote note) {
        val items = note.getItems();

        items.forEach(item -> {
            final Product foundProduct =
                    productRepository.findById(item.getProduct().getId())
                            .orElseThrow(()->new EntityNotFoundException
                                    ("You are trying to sell a non-existing item."));
            foundProduct.increaseStockBy(item.getAmountOrdered());
            productRepository.save(foundProduct);
        });
    }


    @Override
    public GoodsReceivedNoteDTO handle(@NotNull GoodsReceivedNoteDTO goodsReceivedNoteDTO) {
        val insertedNote = insertNote(goodsReceivedNoteDTO);

        this.mediator.emit(insertedNote);
        return insertedNote;
    }
}
