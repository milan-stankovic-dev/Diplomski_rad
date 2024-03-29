package rs.ac.bg.fon.silab.diplomskirad.domain.firm.SOGetAll;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.EmptyRequest;
import rs.ac.bg.fon.silab.diplomskirad.domain.firm.Firm;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.dto.FirmDTO;
import rs.ac.bg.fon.silab.diplomskirad.mapper.FirmMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.FirmRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllFirmsRequestHandler
        implements RequestHandler<EmptyRequest<DTOListResponse<FirmDTO>>,
        DTOListResponse<FirmDTO>> {

    private final FirmRepository repository;
    private final FirmMapper firmMapper;
    private final Mediator mediator;
    private List<FirmDTO> getAllFirms() {
        final List<Firm> foundFirms = repository.findAll();
        return firmMapper
                .listOfEntitiesToListOfDTOs(foundFirms);
    }

    @Override
    public DTOListResponse<FirmDTO> handle(@NotNull EmptyRequest<DTOListResponse<FirmDTO>>
                                                  firmDTOListResponseEmptyRequest) {

        val foundFirmDTOs = getAllFirms();
        val foundFirmsResponse =
                new DTOListResponse<FirmDTO>(foundFirmDTOs);

        this.mediator.emit(foundFirmsResponse);

        return foundFirmsResponse;
    }
}
