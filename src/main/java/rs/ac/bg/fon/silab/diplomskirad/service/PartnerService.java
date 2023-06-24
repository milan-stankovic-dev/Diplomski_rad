package rs.ac.bg.fon.silab.diplomskirad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.domain.Partner;
import rs.ac.bg.fon.silab.diplomskirad.dto.PartnerDTO;
import rs.ac.bg.fon.silab.diplomskirad.mapper.PartnerMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.PartnerRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PartnerService {
    private final PartnerRepository repository;

    public List<PartnerDTO> getAllPartners() {
        List<Partner> foundPartners = repository.findAll();
        return new PartnerMapper().listOfEntitiesToListOfDTOs(foundPartners);
    }
}
