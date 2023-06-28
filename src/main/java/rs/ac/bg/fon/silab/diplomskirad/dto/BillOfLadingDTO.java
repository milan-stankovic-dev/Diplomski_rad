package rs.ac.bg.fon.silab.diplomskirad.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public record BillOfLadingDTO(
        long id,
        Date deadLine,
        Date issueDate,
        BigDecimal totalCost,
        BuyerDTO buyer,
        Set<BillOfLadingItemDTO> items
)
{ }
