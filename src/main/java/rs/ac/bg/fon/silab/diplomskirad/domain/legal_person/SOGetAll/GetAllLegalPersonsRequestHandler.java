package rs.ac.bg.fon.silab.diplomskirad.domain.legal_person.SOGetAll;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.domain.EmptyRequest;
import rs.ac.bg.fon.silab.diplomskirad.domain.legal_person.GetLegalPersonsRequest;
import rs.ac.bg.fon.silab.diplomskirad.domain.legal_person.LegalPerson;
import rs.ac.bg.fon.silab.diplomskirad.dto.LegalPersonDTO;
import rs.ac.bg.fon.silab.diplomskirad.mapper.LegalPersonMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.LegalPersonRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllLegalPersonsRequestHandler
        implements RequestHandler<GetLegalPersonsRequest,
                    DTOListResponse<LegalPersonDTO>> {

    private final Mediator mediator;
    private final LegalPersonRepository repository;

    private List<LegalPersonDTO> getAllLegalPersons() {
        final List<LegalPerson> foundLegalPersons = repository.findAll();

        return new LegalPersonMapper().listOfEntitiesToListOfDTOs(foundLegalPersons);
    }
    @Override
    public DTOListResponse<LegalPersonDTO> handle(@NotNull GetLegalPersonsRequest
                                                              getLegalPersonsRequest) {

        val foundLegalPersonDTOs =
                getAllLegalPersons();
        val legalPersonsResponse =
                new DTOListResponse<LegalPersonDTO>(foundLegalPersonDTOs);

        this.mediator.emit(legalPersonsResponse);
        return legalPersonsResponse;
    }
}
