package rs.ac.bg.fon.silab.diplomskirad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.domain.LegalPerson;
import rs.ac.bg.fon.silab.diplomskirad.dto.LegalPersonDTO;
import rs.ac.bg.fon.silab.diplomskirad.mapper.LegalPersonMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.LegalPersonRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LegalPersonService {
    private final LegalPersonRepository repository;

    public List<LegalPersonDTO> getAllLegalPersons() {
        List<LegalPerson> foundLegalPersons = repository.findAll();

        return new LegalPersonMapper().listOfEntitiesToListOfDTOs(foundLegalPersons);
    }
}
