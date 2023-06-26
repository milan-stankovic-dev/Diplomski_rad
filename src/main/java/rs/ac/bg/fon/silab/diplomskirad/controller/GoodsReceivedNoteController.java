package rs.ac.bg.fon.silab.diplomskirad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.silab.diplomskirad.domain.GoodsReceivedNote;
import rs.ac.bg.fon.silab.diplomskirad.dto.GoodsReceivedNoteDTO;
import rs.ac.bg.fon.silab.diplomskirad.service.GoodsReceivedNoteService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/goods-received-note")
public class GoodsReceivedNoteController {
    private final GoodsReceivedNoteService service;

    @PostMapping
    public ResponseEntity<Object> insertNote(
            @RequestBody GoodsReceivedNoteDTO noteDTO){
        try{
            return ResponseEntity.ok(service.insert(noteDTO));
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }
}
