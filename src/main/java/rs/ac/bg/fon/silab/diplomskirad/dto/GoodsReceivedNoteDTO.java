package rs.ac.bg.fon.silab.diplomskirad.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public record GoodsReceivedNoteDTO(
    long id,
    Date deadLine,
    Date issueDate,
    BigDecimal totalCost,
    PartnerDTO partner,
    Set<GoodsReceivedNoteItemDTO> items
) { }
