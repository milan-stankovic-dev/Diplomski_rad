package rs.ac.bg.fon.silab.diplomskirad.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.report.Report;
import rs.ac.bg.fon.silab.diplomskirad.dto.ReportDTO;

@Component
@RequiredArgsConstructor
public non-sealed class ReportMapper
        implements DtoDomainMapper<ReportDTO, Report> {

    private final ReportItemMapper reportItemMapper;

    @Override
    public ReportDTO entityToDTO(Report report) {
        var reportDTO = new ReportDTO(
                report.getId(),
                report.getReportDate(),
                report.getTotalCapacity(),
                reportItemMapper
                        .setOfEntitiesSetOfDTOs(
                                report.getReportItems())
        );

        return reportDTO;
    }

    @Override
    public Report dTOtoEntity(ReportDTO reportDTO) {
        System.out.println("DtoToEntity 1");
        var report = new Report(
                reportDTO.id(),
                reportDTO.reportDate(),
                reportDTO.totalCapacity(),
                reportItemMapper
                        .setOfDTOsToSetOfEntities(reportDTO.reportItems())
        );

        return report;
    }
}
