package rs.ac.bg.fon.silab.diplomskirad.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.domain.GoodsReceivedNote;
import rs.ac.bg.fon.silab.diplomskirad.domain.GoodsReceivedNoteItem;
import rs.ac.bg.fon.silab.diplomskirad.domain.Product;
import rs.ac.bg.fon.silab.diplomskirad.dto.GoodsReceivedNoteDTO;
import rs.ac.bg.fon.silab.diplomskirad.mapper.GoodsReceivedNoteMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.GoodsReceivedNoteRepository;
import rs.ac.bg.fon.silab.diplomskirad.repository.ProductRepository;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class GoodsReceivedNoteService {
    private final GoodsReceivedNoteRepository repository;
    private final GoodsReceivedNoteMapper noteMapper;
    private final ProductRepository productRepository;
    public GoodsReceivedNoteDTO insertNote(
            GoodsReceivedNoteDTO noteDTO) {
        var note = noteMapper.dTOtoEntity(noteDTO);
        noteItemValidator(note);
        noteTotalPriceValidator(note);
        increaseItemStock(note);
        var savedNote = repository.save(note);

        return noteMapper.entityToDTO(savedNote);
    }
    private void noteItemValidator(GoodsReceivedNote note) {
        if(note == null){
            throw new IllegalArgumentException("Your note must not be empty");
        }
        var items = note.getItems();
        if(items == null || items.size() == 0){
            throw new IllegalStateException("Your goods received note has " +
                    "no items.");
        }
    }

    private void noteTotalPriceValidator(GoodsReceivedNote note) {
        BigDecimal checkSum = BigDecimal.valueOf(0);
        final BigDecimal noteSum = note.getTotalCost();

        for(GoodsReceivedNoteItem item : note.getItems()){
            if(item.getProduct() == null){
                throw new NullPointerException("Your item must refer" +
                        "to a product.");
            }
            checkSum = checkSum.add(item.getProduct().getPrice()
                    .multiply(BigDecimal.valueOf(item.getAmountOrdered())));
        }
        if(!checkSum.equals(noteSum)){
            throw new IllegalStateException("Your note's total does " +
                    "not reflect the true state of the document.");
        }
    }

    private void increaseItemStock(GoodsReceivedNote note) {
        var items = note.getItems();
        for(GoodsReceivedNoteItem item : items){
            Product foundProduct =
                    fetchProductIfPossible(item.getId());

            foundProduct.increaseStockBy(item.getAmountOrdered());
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