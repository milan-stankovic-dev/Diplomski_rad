package rs.ac.bg.fon.silab.masterrad.domain.report;

import io.jkratz.mediator.core.Mediator;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.silab.masterrad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.masterrad.dto.ReportDTO;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/report")
@CrossOrigin(origins = "http://localhost:4200")
public class ReportController {
    private final Mediator mediator;

    @PostMapping
    public ResponseEntity<Object> insertReport (
            @RequestBody ReportDTO reportDTO) throws Exception {
        val savedReport =
            this.mediator.dispatch(reportDTO);

        return ResponseEntity.ok(savedReport);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReportDTO>> getAllReports() {
        final DTOListResponse<ReportDTO> foundReports =
                this.mediator.dispatch(new GetReportsRequest());

        return ResponseEntity.ok(foundReports.dtos());
    }

}