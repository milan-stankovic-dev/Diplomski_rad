package rs.ac.bg.fon.silab.masterrad.domain.natural_person;

import io.jkratz.mediator.core.Mediator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.silab.masterrad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.masterrad.dto.NaturalPersonDTO;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/natural-person")
@CrossOrigin(origins = "http://localhost:4200")
public class NaturalPersonController {
    private final Mediator mediator;

    @GetMapping("/all")
    public ResponseEntity<List<NaturalPersonDTO>> getAllNaturalPersons(){
        final DTOListResponse<NaturalPersonDTO> naturalPersonResponse
                = this.mediator.dispatch(new GetNaturalPersonsRequest());

        return ResponseEntity.ok(naturalPersonResponse.dtos());
    }
}