package rs.ac.bg.fon.silab.masterrad.dto;

import io.jkratz.mediator.core.Event;
import io.jkratz.mediator.core.Request;

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
) implements Request<BillOfLadingDTO>, Event { }
