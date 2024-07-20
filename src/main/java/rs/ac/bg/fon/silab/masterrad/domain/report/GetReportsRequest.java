package rs.ac.bg.fon.silab.masterrad.domain.report;

import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.masterrad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.masterrad.dto.ReportDTO;

public record GetReportsRequest
        () implements Request<DTOListResponse<ReportDTO>> { }
