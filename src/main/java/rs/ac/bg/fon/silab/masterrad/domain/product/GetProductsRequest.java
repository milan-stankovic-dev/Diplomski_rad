package rs.ac.bg.fon.silab.masterrad.domain.product;

import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.masterrad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.masterrad.dto.ProductDTO;

public record GetProductsRequest
        () implements Request<DTOListResponse<ProductDTO>> { }
