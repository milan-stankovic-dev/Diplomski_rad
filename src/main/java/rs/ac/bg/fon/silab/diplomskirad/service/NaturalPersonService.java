package rs.ac.bg.fon.silab.diplomskirad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.repository.NaturalPersonRepository;

@RequiredArgsConstructor
@Service
public class NaturalPersonService {
    private final NaturalPersonRepository repository;
}
