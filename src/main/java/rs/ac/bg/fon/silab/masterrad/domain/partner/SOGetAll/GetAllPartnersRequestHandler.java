package rs.ac.bg.fon.silab.masterrad.domain.partner.SOGetAll;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.masterrad.domain.partner.GetPartnersRequest;
import rs.ac.bg.fon.silab.masterrad.domain.partner.Partner;
import rs.ac.bg.fon.silab.masterrad.dto.PartnerDTO;
import rs.ac.bg.fon.silab.masterrad.mapper.PartnerMapper;
import rs.ac.bg.fon.silab.masterrad.repository.PartnerRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllPartnersRequestHandler
        implements RequestHandler<GetPartnersRequest,
        DTOListResponse<PartnerDTO>> {

    private final Mediator mediator;
    private final PartnerRepository repository;

    private List<PartnerDTO> getAllPartners() {
        final List<Partner> foundPartners = repository.findAll();
        return new PartnerMapper().listOfEntitiesToListOfDTOs(foundPartners);
    }

    @Override
    public DTOListResponse<PartnerDTO> handle(
            @NotNull GetPartnersRequest getPartnersRequest) {

        val foundPartnerDTOs = getAllPartners();
        val foundPartnersResponse =
                new DTOListResponse<PartnerDTO>(foundPartnerDTOs);

        this.mediator.emit(foundPartnersResponse);

        return foundPartnersResponse;
    }
}
