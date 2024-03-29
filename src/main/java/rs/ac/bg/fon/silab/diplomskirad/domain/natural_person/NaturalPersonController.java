package rs.ac.bg.fon.silab.diplomskirad.domain.natural_person;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.silab.diplomskirad.dto.NaturalPersonDTO;
import rs.ac.bg.fon.silab.diplomskirad.service.NaturalPersonService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/natural-person")
public class NaturalPersonController {
    private final NaturalPersonService service;

    @GetMapping("/all")
    public ResponseEntity<List<NaturalPersonDTO>> getAllNaturalPersons(){
        var foundNaturalPerons = service.getAllNaturalPersons();
        return ResponseEntity.ok(foundNaturalPerons);
    }
}