package rs.ac.bg.fon.silab.diplomskirad.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.bill_of_lading.BillOfLading;
import rs.ac.bg.fon.silab.diplomskirad.dto.BillOfLadingDTO;

@Component
@RequiredArgsConstructor
public non-sealed class BillOfLadingMapper implements DtoDomainMapper<BillOfLadingDTO, BillOfLading> {
    private final BillOfLadingItemMapper itemMapper;
    private final BuyerMapper buyerMapper;
    @Override
    public BillOfLadingDTO entityToDTO(BillOfLading bill) {
        var billDTO = new BillOfLadingDTO(
                bill.getId(),
                bill.getDeadLine(),
                bill.getIssueDate(),
                bill.getTotalCost(),
                buyerMapper.entityToDTO(bill.getBuyer()),
                itemMapper.setOfEntitiesSetOfDTOs(bill.getItems())
        );

        return billDTO;
    }

    @Override
    public BillOfLading dTOtoEntity(BillOfLadingDTO billDTO) {
        var bill = new BillOfLading(
                billDTO.id(),
                billDTO.deadLine(),
                billDTO.issueDate(),
                billDTO.totalCost(),
                buyerMapper.dTOtoEntity(billDTO.buyer()),
                itemMapper.setOfDTOsToSetOfEntities(billDTO.items())
        );

        return bill;
    }
}
