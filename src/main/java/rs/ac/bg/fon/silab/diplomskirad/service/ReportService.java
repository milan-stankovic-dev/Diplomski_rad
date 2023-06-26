package rs.ac.bg.fon.silab.diplomskirad.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.domain.Report;
import rs.ac.bg.fon.silab.diplomskirad.domain.ReportItem;
import rs.ac.bg.fon.silab.diplomskirad.dto.ReportDTO;
import rs.ac.bg.fon.silab.diplomskirad.dto.ReportItemDTO;
import rs.ac.bg.fon.silab.diplomskirad.mapper.ReportItemMapper;
import rs.ac.bg.fon.silab.diplomskirad.mapper.ReportMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.ReportItemRepository;
import rs.ac.bg.fon.silab.diplomskirad.repository.ReportRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;
    private final ReportItemRepository reportItemRepository;

    public ReportDTO saveReport(ReportDTO reportDTO) throws Exception{

        var report = reportMapper.dTOtoEntity(reportDTO);
        var savedReport = reportRepository.save(report);

        return reportMapper.entityToDTO(report);
    }



    public List<ReportDTO> getAllReports() {
        return reportMapper.listOfEntitiesToListOfDTOs(
                reportRepository.findAll());
    }
}
