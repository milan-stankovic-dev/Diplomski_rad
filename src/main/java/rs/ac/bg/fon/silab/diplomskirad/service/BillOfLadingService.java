package rs.ac.bg.fon.silab.diplomskirad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.repository.BillOfLadingRepository;

@RequiredArgsConstructor
@Service
public class BillOfLadingService {
    private final BillOfLadingRepository repository;
}
