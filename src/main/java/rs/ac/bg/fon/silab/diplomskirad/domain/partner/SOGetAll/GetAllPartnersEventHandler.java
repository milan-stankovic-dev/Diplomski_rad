package rs.ac.bg.fon.silab.diplomskirad.domain.partner.SOGetAll;

import io.jkratz.mediator.core.EventHandler;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.dto.PartnerDTO;

@Component
@Log
public class GetAllPartnersEventHandler
        implements EventHandler<DTOListResponse<PartnerDTO>> {
    @Override
    public void handle(@NotNull DTOListResponse<PartnerDTO>
                                   partnerDTODTOListResponse) {

        log.info("All partners fetched from the database.");
    }
}
