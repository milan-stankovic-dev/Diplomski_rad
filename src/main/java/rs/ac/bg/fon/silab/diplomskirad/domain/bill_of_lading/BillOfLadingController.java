package rs.ac.bg.fon.silab.diplomskirad.domain.bill_of_lading;

import io.jkratz.mediator.core.Mediator;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.silab.diplomskirad.dto.BillOfLadingDTO;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/bill-of-lading")
@CrossOrigin(origins = "http://localhost:4200")
public class BillOfLadingController {
    private final Mediator mediator;

    @PostMapping
    public ResponseEntity<Object> insertBillOfLading(
            @RequestBody BillOfLadingDTO billOfLadingDTO){
        val savedBill = this.mediator.dispatch(billOfLadingDTO);
        return ResponseEntity.ok(savedBill);
    }


}
