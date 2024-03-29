package rs.ac.bg.fon.silab.diplomskirad.domain.report;

import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.dto.ReportDTO;

public record GetReportsRequest
        () implements Request<DTOListResponse<ReportDTO>> { }
