package rs.ac.bg.fon.silab.diplomskirad.domain.legal_person.SOGetAll;

import io.jkratz.mediator.core.EventHandler;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.dto.LegalPersonDTO;

@Component
@Log
public class GetAllLegalPersonsEventHandler
        implements EventHandler<DTOListResponse<LegalPersonDTO>> {
    @Override
    public void handle(@NotNull DTOListResponse<LegalPersonDTO> legalPersonDTODTOListResponse) {
        log.info("Fetched all legal persons from the database.");
    }
}
