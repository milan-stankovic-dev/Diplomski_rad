package rs.ac.bg.fon.silab.masterrad.domain.report.SOInsert;

import io.jkratz.mediator.core.EventHandler;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.dto.ReportDTO;

@Component
@Log
public class InsertReportEventHandler
        implements EventHandler<ReportDTO> {

    @Override
    public void handle(@NotNull ReportDTO reportDTO) {
        log.info("Inserted report in the db. " + reportDTO);
    }
}
