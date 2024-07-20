package rs.ac.bg.fon.silab.masterrad.domain.report.SOGetAll;

import io.jkratz.mediator.core.EventHandler;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.masterrad.dto.ReportDTO;

@Component
@Log
public class GetAllReportsEventHandler
        implements EventHandler<DTOListResponse<ReportDTO>> {
    @Override
    public void handle(@NotNull DTOListResponse<ReportDTO> reportDTODTOListResponse) {
        log.info("Fetched all reports from the database.");
    }
}
