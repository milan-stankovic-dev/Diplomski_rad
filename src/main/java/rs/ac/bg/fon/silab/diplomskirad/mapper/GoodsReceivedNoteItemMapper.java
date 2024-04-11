package rs.ac.bg.fon.silab.diplomskirad.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.goods_received_note_item.GoodsReceivedNoteItem;
import rs.ac.bg.fon.silab.diplomskirad.dto.GoodsReceivedNoteItemDTO;

@Component
@RequiredArgsConstructor
public non-sealed class GoodsReceivedNoteItemMapper
        implements DtoDomainMapper<GoodsReceivedNoteItemDTO, GoodsReceivedNoteItem> {

    private final ProductMapper productMapper;

    @Override
    public GoodsReceivedNoteItemDTO entityToDTO(GoodsReceivedNoteItem noteItem) {
        var itemDTO = new GoodsReceivedNoteItemDTO(
                noteItem.getId(),
                noteItem.getAmountOrdered(),
                productMapper.entityToDTO(noteItem.getProduct())
        );

        return itemDTO;
    }

    @Override
    public GoodsReceivedNoteItem dTOtoEntity(GoodsReceivedNoteItemDTO noteItemDTO) {
        var item = new GoodsReceivedNoteItem(
                noteItemDTO.id(),
                noteItemDTO.amountOrdered(),
                productMapper.dTOtoEntity(noteItemDTO.product())
        );

        return item;
    }
}
