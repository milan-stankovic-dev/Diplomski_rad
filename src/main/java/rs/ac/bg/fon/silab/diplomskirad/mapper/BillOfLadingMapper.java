package rs.ac.bg.fon.silab.diplomskirad.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.BillOfLading;
import rs.ac.bg.fon.silab.diplomskirad.dto.BillOfLadingDTO;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public non-sealed class BillOfLadingMapper implements DtoDomainMapper<BillOfLadingDTO, BillOfLading> {
    private final BillOfLadingItemMapper itemMapper;

    @Override
    public BillOfLadingDTO entityToDTO(BillOfLading bill) {
        var billDTO = new BillOfLadingDTO(
                bill.getId(),
                bill.getDeadLine(),
                bill.getIssueDate(),
                bill.getTotalCost(),
                bill.getBuyer(),
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
                billDTO.buyer(),
                itemMapper.setOfDTOsToSetOfEntities(billDTO.items())
        );

        return bill;
    }
}
