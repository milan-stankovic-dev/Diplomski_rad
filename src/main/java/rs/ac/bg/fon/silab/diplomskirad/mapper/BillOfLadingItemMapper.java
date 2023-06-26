package rs.ac.bg.fon.silab.diplomskirad.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.BillOfLadingItem;
import rs.ac.bg.fon.silab.diplomskirad.domain.Product;
import rs.ac.bg.fon.silab.diplomskirad.dto.BillOfLadingDTO;
import rs.ac.bg.fon.silab.diplomskirad.dto.BillOfLadingItemDTO;

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
