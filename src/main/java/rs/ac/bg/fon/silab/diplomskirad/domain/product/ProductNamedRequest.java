package rs.ac.bg.fon.silab.diplomskirad.domain.product;

import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.dto.ProductDTO;

public record ProductNamedRequest(
    String name
) implements Request<DTOListResponse<ProductDTO>> { }
