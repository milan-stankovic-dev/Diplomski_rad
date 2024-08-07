package rs.ac.bg.fon.silab.masterrad.domain.product.SOInsert;

import io.jkratz.mediator.core.EventHandler;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.dto.ProductDTO;

@Component
@Log
public class InsertProductEventHandler
        implements EventHandler<ProductDTO> {
    @Override
    public void handle(@NotNull ProductDTO productDTO) {
        log.info("Product inserted! " + productDTO);
    }
}
