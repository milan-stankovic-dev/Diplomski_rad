package rs.ac.bg.fon.silab.diplomskirad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.repository.ReportRepository;
@RequiredArgsConstructor
@Service
public class ReportService {
    private final ReportRepository repository;
}
