package rs.ac.bg.fon.silab.diplomskirad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.domain.firm.Firm;
import rs.ac.bg.fon.silab.diplomskirad.dto.FirmDTO;
import rs.ac.bg.fon.silab.diplomskirad.mapper.FirmMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.FirmRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FirmService {
    private final FirmRepository repository;
    private final FirmMapper firmMapper;

    public List<FirmDTO> getAllFirms() {
        List<Firm> foundFirms = repository.findAll();
        return firmMapper
                .listOfEntitiesToListOfDTOs(foundFirms);
    }
}
