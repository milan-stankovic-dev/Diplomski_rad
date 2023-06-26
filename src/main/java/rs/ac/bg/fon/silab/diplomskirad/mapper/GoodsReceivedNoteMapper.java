package rs.ac.bg.fon.silab.diplomskirad.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.GoodsReceivedNote;
import rs.ac.bg.fon.silab.diplomskirad.dto.GoodsReceivedNoteDTO;
@Component
@RequiredArgsConstructor
public non-sealed class GoodsReceivedNoteMapper
        implements DtoDomainMapper<GoodsReceivedNoteDTO, GoodsReceivedNote>{
    private final GoodsReceivedNoteItemMapper noteItemMapper;
    private final PartnerMapper partnerMapper;

    @Override
    public GoodsReceivedNoteDTO entityToDTO(GoodsReceivedNote note) {
        var noteDTO = new GoodsReceivedNoteDTO(
                note.getId(),
                note.getDeadLine(),
                note.getIssueDate(),
                note.getTotalCost(),
                partnerMapper.entityToDTO(note.getPartner()),
                noteItemMapper.setOfEntitiesSetOfDTOs(note.getItems())
        );

        return noteDTO;
    }

    @Override
    public GoodsReceivedNote dTOtoEntity(GoodsReceivedNoteDTO noteDTO) {
        var note = new GoodsReceivedNote(
                noteDTO.id(),
                noteDTO.deadLine(),
                noteDTO.issueDate(),
                noteDTO.totalCost(),
                partnerMapper.dTOtoEntity(noteDTO.partner()),
                noteItemMapper.setOfDTOsToSetOfEntities(noteDTO.items())
        );

        return note;
    }
}
