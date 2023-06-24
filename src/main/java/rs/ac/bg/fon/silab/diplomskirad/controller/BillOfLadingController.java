package rs.ac.bg.fon.silab.diplomskirad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.silab.diplomskirad.domain.BillOfLading;
import rs.ac.bg.fon.silab.diplomskirad.service.BillOfLadingService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/bill-of-lading")
public class BillOfLadingController {
    private final BillOfLadingService service;

    @PostMapping
    public ResponseEntity<BillOfLading> insertBillOfLading(
            @RequestBody BillOfLading billOfLading){
        return ResponseEntity.ok(service.insertBillOfLading(billOfLading));
    }
}
