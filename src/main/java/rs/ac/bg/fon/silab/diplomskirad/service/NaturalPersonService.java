package rs.ac.bg.fon.silab.diplomskirad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.domain.natural_person.NaturalPerson;
import rs.ac.bg.fon.silab.diplomskirad.dto.NaturalPersonDTO;
import rs.ac.bg.fon.silab.diplomskirad.mapper.NaturalPersonMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.NaturalPersonRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NaturalPersonService {
    private final NaturalPersonRepository repository;

    public List<NaturalPersonDTO> getAllNaturalPersons() {
        List<NaturalPerson> foundNaturalPersons = repository.findAll();

        return new NaturalPersonMapper().
                listOfEntitiesToListOfDTOs(foundNaturalPersons);
    }
}
