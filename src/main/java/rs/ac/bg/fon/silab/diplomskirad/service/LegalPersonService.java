package rs.ac.bg.fon.silab.diplomskirad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.repository.LegalPersonRepository;

@RequiredArgsConstructor
@Service
public class LegalPersonService {
    private final LegalPersonRepository repository;
}
