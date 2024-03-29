package rs.ac.bg.fon.silab.diplomskirad.domain.natural_person.SOGetAll;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.domain.EmptyRequest;
import rs.ac.bg.fon.silab.diplomskirad.domain.natural_person.NaturalPerson;
import rs.ac.bg.fon.silab.diplomskirad.dto.NaturalPersonDTO;
import rs.ac.bg.fon.silab.diplomskirad.mapper.NaturalPersonMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.NaturalPersonRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllNaturalPersonsRequestHandler
        implements RequestHandler<EmptyRequest<DTOListResponse<NaturalPersonDTO>>,
            DTOListResponse<NaturalPersonDTO>> {

    private final Mediator mediator;
    private final NaturalPersonRepository repository;

    private List<NaturalPersonDTO> getAllNaturalPersons() {
        final List<NaturalPerson> foundNaturalPersons = repository.findAll();

        return new NaturalPersonMapper().listOfEntitiesToListOfDTOs(foundNaturalPersons);
    }
    @Override
    public DTOListResponse<NaturalPersonDTO> handle(
            @NotNull EmptyRequest<DTOListResponse<NaturalPersonDTO>>
                    dtoListResponseEmptyRequest) {

            val foundNaturalPersons =
                    getAllNaturalPersons();
            val naturalPersonsResponse =
                    new DTOListResponse<NaturalPersonDTO>(foundNaturalPersons);

            this.mediator.emit(naturalPersonsResponse);

            return naturalPersonsResponse;
    }
}
