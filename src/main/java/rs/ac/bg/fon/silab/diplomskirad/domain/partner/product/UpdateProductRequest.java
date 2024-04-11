package rs.ac.bg.fon.silab.diplomskirad.domain.partner.product;

import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.diplomskirad.dto.ProductDTO;

public record UpdateProductRequest(
        ProductDTO productDTO,
        Long id
) implements Request<ProductDTO>
{ }
