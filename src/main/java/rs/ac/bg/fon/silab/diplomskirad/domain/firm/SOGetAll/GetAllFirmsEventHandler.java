package rs.ac.bg.fon.silab.diplomskirad.domain.firm.SOGetAll;

import io.jkratz.mediator.core.EventHandler;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.dto.FirmDTO;

@Component
@Log
public class GetAllFirmsEventHandler
        implements EventHandler<DTOListResponse<FirmDTO>> {
    @Override
    public void handle(@NotNull DTOListResponse<FirmDTO>
                                   firmDTOListResponse) {

        log.info("All firms fetched from the database.");
    }
}
