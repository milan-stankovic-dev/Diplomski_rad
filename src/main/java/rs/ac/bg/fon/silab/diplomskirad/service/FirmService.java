package rs.ac.bg.fon.silab.diplomskirad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.repository.FirmRepository;

@RequiredArgsConstructor
@Service
public class FirmService {
    private final FirmRepository repository;
}
