package rs.ac.bg.fon.silab.diplomskirad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.silab.diplomskirad.dto.BillOfLadingDTO;
import rs.ac.bg.fon.silab.diplomskirad.service.BillOfLadingService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/bill-of-lading-old")
@CrossOrigin(origins = "http://localhost:4200")
public class BillOfLadingControllerOld {
    private final BillOfLadingService service;

    @PostMapping("/old")
    public ResponseEntity<Object> insertBillOfLadingOld(
            @RequestBody BillOfLadingDTO billOfLadingDTO){
        try{
            return ResponseEntity.ok(service.insertBillOfLading(billOfLadingDTO));
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }
}
