package rs.ac.bg.fon.silab.masterrad.dto;

import io.jkratz.mediator.core.Event;
import io.jkratz.mediator.core.Request;

import java.util.Date;
import java.util.Set;

public record ReportDTO(
        long id,
        Date reportDate,
        double totalCapacity,
        Set<ReportItemDTO> reportItems
) implements Request<ReportDTO>, Event { }
