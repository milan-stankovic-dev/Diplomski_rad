package rs.ac.bg.fon.silab.diplomskirad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.repository.PartnerRepository;

@RequiredArgsConstructor
@Service
public class PartnerService {
    private final PartnerRepository repository;
}
