package rs.ac.bg.fon.silab.diplomskirad.domain.partner;

import io.jkratz.mediator.core.Mediator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.domain.EmptyRequest;
import rs.ac.bg.fon.silab.diplomskirad.dto.PartnerDTO;
import rs.ac.bg.fon.silab.diplomskirad.service.PartnerService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/partner")
public class PartnerController {
    private final Mediator mediator;

    @GetMapping("/all")
    public ResponseEntity<List<PartnerDTO>> getAllPartners() {
        final DTOListResponse<PartnerDTO> foundPartners =
                mediator.dispatch(new GetPartnersRequest());

        return ResponseEntity.ok(foundPartners.dtos());
    }
}