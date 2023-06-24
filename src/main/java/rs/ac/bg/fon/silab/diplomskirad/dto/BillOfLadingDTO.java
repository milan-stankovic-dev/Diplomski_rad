package rs.ac.bg.fon.silab.diplomskirad.dto;

import rs.ac.bg.fon.silab.diplomskirad.domain.BillOfLadingItem;
import rs.ac.bg.fon.silab.diplomskirad.domain.abstraction.Buyer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public record BillOfLadingDTO(
        long id,
        Date deadLine,
        Date issueDate,
        BigDecimal totalCost,
        Buyer buyer,
        Set<BillOfLadingItem> items
)
{ }
