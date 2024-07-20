package rs.ac.bg.fon.silab.masterrad.domain.product.SOGetAllNamed;

import io.jkratz.mediator.core.EventHandler;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.masterrad.dto.ProductDTO;

@Component
@Log
public class GetAllProductsNamedEventHandler
        implements EventHandler<DTOListResponse<ProductDTO>> {
    @Override
    public void handle(@NotNull DTOListResponse<ProductDTO> productDTODTOListResponse) {
        log.info("Found all products with given name. List contains " +
                productDTODTOListResponse.dtos().size() + " entries.");
    }
}
