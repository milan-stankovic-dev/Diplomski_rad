package rs.ac.bg.fon.silab.masterrad.domain.natural_person.SOGetAll;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.masterrad.domain.natural_person.GetNaturalPersonsRequest;
import rs.ac.bg.fon.silab.masterrad.domain.natural_person.NaturalPerson;
import rs.ac.bg.fon.silab.masterrad.dto.NaturalPersonDTO;
import rs.ac.bg.fon.silab.masterrad.mapper.NaturalPersonMapper;
import rs.ac.bg.fon.silab.masterrad.repository.NaturalPersonRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllNaturalPersonsRequestHandler
        implements RequestHandler<GetNaturalPersonsRequest,
        DTOListResponse<NaturalPersonDTO>> {

    private final Mediator mediator;
    private final NaturalPersonRepository repository;

    private List<NaturalPersonDTO> getAllNaturalPersons() {
        final List<NaturalPerson> foundNaturalPersons = repository.findAll();

        return new NaturalPersonMapper().listOfEntitiesToListOfDTOs(foundNaturalPersons);
    }

    @Override
    public DTOListResponse<NaturalPersonDTO> handle(
            @NotNull GetNaturalPersonsRequest
                    getNaturalPersonsRequest) {

        val foundNaturalPersons =
                getAllNaturalPersons();
        val naturalPersonsResponse =
                new DTOListResponse<NaturalPersonDTO>(foundNaturalPersons);

        this.mediator.emit(naturalPersonsResponse);

        return naturalPersonsResponse;
    }
}
