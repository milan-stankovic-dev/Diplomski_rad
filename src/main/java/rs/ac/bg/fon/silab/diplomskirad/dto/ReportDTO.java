package rs.ac.bg.fon.silab.diplomskirad.dto;

import java.util.Date;
import java.util.Set;

public record ReportDTO(
        long id,
        Date reportDate,
        double totalCapacity,
        Set<ReportItemDTO> reportItems
) { }
