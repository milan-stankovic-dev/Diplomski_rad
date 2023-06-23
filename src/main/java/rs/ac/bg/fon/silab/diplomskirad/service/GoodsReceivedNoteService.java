package rs.ac.bg.fon.silab.diplomskirad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.repository.GoodsReceivedNoteRepository;

@RequiredArgsConstructor
@Service
public class GoodsReceivedNoteService {
    private final GoodsReceivedNoteRepository repository;
}
