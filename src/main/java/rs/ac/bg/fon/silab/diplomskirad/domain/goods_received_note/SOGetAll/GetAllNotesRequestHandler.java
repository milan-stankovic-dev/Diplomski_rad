package rs.ac.bg.fon.silab.diplomskirad.domain.goods_received_note.SOGetAll;

import io.jkratz.mediator.core.EventHandler;
import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.domain.EmptyRequest;
import rs.ac.bg.fon.silab.diplomskirad.domain.goods_received_note.GetNotesRequest;
import rs.ac.bg.fon.silab.diplomskirad.dto.GoodsReceivedNoteDTO;
import rs.ac.bg.fon.silab.diplomskirad.mapper.GoodsReceivedNoteMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.GoodsReceivedNoteRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllNotesRequestHandler
        implements RequestHandler<GetNotesRequest,
                    DTOListResponse<GoodsReceivedNoteDTO>> {

    private final Mediator mediator;
    private final GoodsReceivedNoteRepository repository;
    private final GoodsReceivedNoteMapper noteMapper;

    private List<GoodsReceivedNoteDTO> getAllNotes() {
        return noteMapper.listOfEntitiesToListOfDTOs(repository.findAll());
    }
    @Override
    public DTOListResponse<GoodsReceivedNoteDTO> handle(@NotNull GetNotesRequest getNotesRequest) {
        val foundNotes =
                getAllNotes();
        val notesResponse =
                new DTOListResponse<GoodsReceivedNoteDTO>(foundNotes);

        this.mediator.emit(notesResponse);
        return notesResponse;
    }
}
