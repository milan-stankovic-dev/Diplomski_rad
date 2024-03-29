package rs.ac.bg.fon.silab.diplomskirad.domain.goods_received_note;

import io.jkratz.mediator.core.Mediator;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.domain.EmptyRequest;
import rs.ac.bg.fon.silab.diplomskirad.dto.GoodsReceivedNoteDTO;
import rs.ac.bg.fon.silab.diplomskirad.service.GoodsReceivedNoteService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/goods-received-note")
@CrossOrigin(origins = "http://localhost:4200")
public class GoodsReceivedNoteController {
    private final GoodsReceivedNoteService service;
    private final Mediator mediator;

    @PostMapping
    public ResponseEntity<Object> insertNote(
            @RequestBody GoodsReceivedNoteDTO noteDTO) {

        val insertedNote =
                this.mediator.dispatch(noteDTO);
        return ResponseEntity.ok(insertedNote);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<GoodsReceivedNoteDTO>> getAll() {
        final DTOListResponse<GoodsReceivedNoteDTO> foundNotes =
                this.mediator.dispatch(new GetNotesRequest());

        return ResponseEntity.ok(foundNotes.dtos());
    }
}