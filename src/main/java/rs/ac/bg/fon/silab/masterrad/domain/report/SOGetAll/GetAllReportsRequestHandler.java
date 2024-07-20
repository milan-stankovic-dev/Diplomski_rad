package rs.ac.bg.fon.silab.masterrad.domain.report.SOGetAll;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.masterrad.domain.report.GetReportsRequest;
import rs.ac.bg.fon.silab.masterrad.dto.ReportDTO;
import rs.ac.bg.fon.silab.masterrad.mapper.ReportMapper;
import rs.ac.bg.fon.silab.masterrad.repository.ReportRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllReportsRequestHandler
        implements RequestHandler<GetReportsRequest,
        DTOListResponse<ReportDTO>> {

    private final Mediator mediator;
    private final ReportRepository repository;
    private final ReportMapper reportMapper;

    private List<ReportDTO> getAllReports() {
        return reportMapper.listOfEntitiesToListOfDTOs(
                repository.findAll());
    }
    @Override
    public DTOListResponse<ReportDTO> handle(@NotNull GetReportsRequest
                                                         getReportsRequest) {
        val reportsFound =
                getAllReports();
        val reportsResponse =
                new DTOListResponse<ReportDTO>(reportsFound);

        this.mediator.emit(reportsResponse);
        return reportsResponse;
    }
}
