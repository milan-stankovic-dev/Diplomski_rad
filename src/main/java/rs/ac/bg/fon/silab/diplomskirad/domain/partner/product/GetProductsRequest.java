package rs.ac.bg.fon.silab.diplomskirad.domain.partner.product;

import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.dto.ProductDTO;

public record GetProductsRequest
        () implements Request<DTOListResponse<ProductDTO>> { }
