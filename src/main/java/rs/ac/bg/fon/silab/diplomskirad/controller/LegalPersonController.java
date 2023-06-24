package rs.ac.bg.fon.silab.diplomskirad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.silab.diplomskirad.domain.LegalPerson;
import rs.ac.bg.fon.silab.diplomskirad.dto.LegalPersonDTO;
import rs.ac.bg.fon.silab.diplomskirad.service.LegalPersonService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/legal-person")
public class LegalPersonController {
    private final LegalPersonService service;

    @GetMapping("/all")
    public ResponseEntity<List<LegalPersonDTO>> getAllLegalPersons(){
        var foundLegalPersonsList = service.getAllLegalPersons();
        return ResponseEntity.ok(foundLegalPersonsList);
    }
}
