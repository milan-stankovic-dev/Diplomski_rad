package rs.ac.bg.fon.silab.masterrad.domain.legal_person.SOGetAll;

import io.jkratz.mediator.core.EventHandler;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.masterrad.dto.LegalPersonDTO;

@Component
@Log
public class GetAllLegalPersonsEventHandler
        implements EventHandler<DTOListResponse<LegalPersonDTO>> {
    @Override
    public void handle(@NotNull DTOListResponse<LegalPersonDTO> legalPersonDTODTOListResponse) {
        log.info("Fetched all legal persons from the database.");
    }
}
