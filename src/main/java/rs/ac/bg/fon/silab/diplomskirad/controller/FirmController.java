package rs.ac.bg.fon.silab.diplomskirad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.silab.diplomskirad.domain.Firm;
import rs.ac.bg.fon.silab.diplomskirad.dto.FirmDTO;
import rs.ac.bg.fon.silab.diplomskirad.service.FirmService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/firm")
public class FirmController {
    private final FirmService service;

    @GetMapping("/all")
    public ResponseEntity<List<FirmDTO>> getAllFirms(){
        var foundFirmsList = service.getAllFirms();
        return ResponseEntity.ok(foundFirmsList);
    }
}
