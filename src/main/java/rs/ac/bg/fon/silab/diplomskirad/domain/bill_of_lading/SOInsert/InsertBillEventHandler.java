package rs.ac.bg.fon.silab.diplomskirad.domain.bill_of_lading.SOInsert;

import io.jkratz.mediator.core.EventHandler;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.dto.BillOfLadingDTO;

@Component
@Log
public class InsertBillEventHandler implements EventHandler<BillOfLadingDTO> {
    @Override
    public void handle(@NotNull BillOfLadingDTO billOfLadingDTO) {
        log.info("BILL OF LADING INSERTED. BILL: " + billOfLadingDTO);
    }
}
