package rs.ac.bg.fon.silab.masterrad.domain.product;

import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.masterrad.dto.ProductDTO;

public record UpdateProductRequest(
        ProductDTO productDTO,
        Long id
) implements Request<ProductDTO>
{ }
