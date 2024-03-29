package rs.ac.bg.fon.silab.diplomskirad.domain.firm;

import io.jkratz.mediator.core.Mediator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.domain.EmptyRequest;
import rs.ac.bg.fon.silab.diplomskirad.dto.FirmDTO;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/firm")
@CrossOrigin(origins = "http://localhost:4200")
public class FirmController {
    private final Mediator mediator;
    @GetMapping("/all")
    public ResponseEntity<List<FirmDTO>> getAllFirms() {
        final DTOListResponse<FirmDTO> response =
                this.mediator.dispatch(new EmptyRequest<>());

        return ResponseEntity.ok(response.dtos());
    }
}
