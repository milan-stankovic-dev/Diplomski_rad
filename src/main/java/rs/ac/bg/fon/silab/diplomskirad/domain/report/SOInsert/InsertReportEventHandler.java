package rs.ac.bg.fon.silab.diplomskirad.domain.report.SOInsert;

import io.jkratz.mediator.core.EventHandler;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.dto.ReportDTO;

@Component
@Log
public class InsertReportEventHandler
        implements EventHandler<DTOListResponse<ReportDTO>> {
    @Override
    public void handle(@NotNull DTOListResponse<ReportDTO> reportDTODTOListResponse) {
        log.info("Fetched all reports from the database.");
    }
}
