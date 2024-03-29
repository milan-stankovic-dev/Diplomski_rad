package rs.ac.bg.fon.silab.diplomskirad.domain.goods_received_note;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.silab.diplomskirad.dto.GoodsReceivedNoteDTO;
import rs.ac.bg.fon.silab.diplomskirad.service.GoodsReceivedNoteService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/goods-received-note")
public class GoodsReceivedNoteController {
    private final GoodsReceivedNoteService service;

    @PostMapping
    public ResponseEntity<Object> insertNote(
            @RequestBody GoodsReceivedNoteDTO noteDTO) {
        try{
            return ResponseEntity.ok(service.insertNote(noteDTO));
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<GoodsReceivedNoteDTO>> getAll(){
        return ResponseEntity.ok(service.getAllNotes());
    }
}