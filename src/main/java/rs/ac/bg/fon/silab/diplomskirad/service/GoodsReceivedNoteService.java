package rs.ac.bg.fon.silab.diplomskirad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.domain.GoodsReceivedNote;
import rs.ac.bg.fon.silab.diplomskirad.dto.GoodsReceivedNoteDTO;
import rs.ac.bg.fon.silab.diplomskirad.mapper.GoodsReceivedNoteMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.GoodsReceivedNoteRepository;

@RequiredArgsConstructor
@Service
public class GoodsReceivedNoteService {
    private final GoodsReceivedNoteRepository repository;
    private final GoodsReceivedNoteMapper noteMapper;
    public GoodsReceivedNoteDTO insert(GoodsReceivedNoteDTO noteDTO) {
        var note = noteMapper.dTOtoEntity(noteDTO);
        var savedNote = repository.save(note);

        return noteMapper.entityToDTO(savedNote);
    }
}
