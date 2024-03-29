package rs.ac.bg.fon.silab.diplomskirad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.silab.diplomskirad.dto.ReportDTO;
import rs.ac.bg.fon.silab.diplomskirad.service.ReportService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/report-old")
public class ReportControllerOld {
    private final ReportService service;

    @PostMapping
    public ResponseEntity<Object> insertReport (
            @RequestBody ReportDTO reportDTO) throws Exception {

            return ResponseEntity.ok(service.insertReport(reportDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReportDTO>> getAllReports(){
        return ResponseEntity.ok(service.getAllReports());
    }

}
