package rs.ac.bg.fon.silab.diplomskirad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.silab.diplomskirad.dto.BillOfLadingDTO;
import rs.ac.bg.fon.silab.diplomskirad.service.BillOfLadingService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/bill-of-lading")
public class BillOfLadingController {
    private final BillOfLadingService service;

    @PostMapping
    public ResponseEntity<Object> insertBillOfLading(
            @RequestBody BillOfLadingDTO billOfLadingDTO){
        try{
            return ResponseEntity.ok(service.insertBillOfLading(billOfLadingDTO));
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }
}
