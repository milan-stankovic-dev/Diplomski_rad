package rs.ac.bg.fon.silab.diplomskirad.domain.legal_person;

import io.jkratz.mediator.core.Mediator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.domain.EmptyRequest;
import rs.ac.bg.fon.silab.diplomskirad.dto.LegalPersonDTO;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/legal-person")
@CrossOrigin(origins = "http://localhost:4200")
public class LegalPersonController {
    private final Mediator mediator;
    @GetMapping("/all")
    public ResponseEntity<List<LegalPersonDTO>> getAllLegalPersons() {
        final DTOListResponse<LegalPersonDTO> legalPersonsResponse
                = this.mediator.dispatch(new GetLegalPersonsRequest());

        return ResponseEntity.ok(legalPersonsResponse.dtos());
    }
}