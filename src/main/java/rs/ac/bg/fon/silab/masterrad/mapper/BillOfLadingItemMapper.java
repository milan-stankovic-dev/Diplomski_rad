package rs.ac.bg.fon.silab.masterrad.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.domain.bill_of_lading_item.BillOfLadingItem;
import rs.ac.bg.fon.silab.masterrad.dto.BillOfLadingItemDTO;

@Component
@RequiredArgsConstructor
public non-sealed class BillOfLadingItemMapper
        implements DtoDomainMapper<BillOfLadingItemDTO, BillOfLadingItem> {
    private final ProductMapper productMapper;

    @Override
    public BillOfLadingItemDTO entityToDTO(BillOfLadingItem item) {
        var itemDTO = new BillOfLadingItemDTO(
                item.getId(),
                item.getAmountSold(),
                productMapper.entityToDTO(item.getProduct())
        );

        return itemDTO;
    }

    @Override
    public BillOfLadingItem dTOtoEntity(BillOfLadingItemDTO itemDTO) {
        var item = new BillOfLadingItem(
                itemDTO.id(),
                itemDTO.amountSold(),
                productMapper.dTOtoEntity(itemDTO.product())
        );

        return item;
    }
}
