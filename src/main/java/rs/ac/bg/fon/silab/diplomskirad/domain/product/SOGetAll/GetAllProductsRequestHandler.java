package rs.ac.bg.fon.silab.diplomskirad.domain.product.SOGetAll;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.domain.EmptyRequest;
import rs.ac.bg.fon.silab.diplomskirad.dto.ProductDTO;
import rs.ac.bg.fon.silab.diplomskirad.mapper.ProductMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.ProductRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllProductsRequestHandler
        implements RequestHandler<EmptyRequest<DTOListResponse<ProductDTO>>,
                DTOListResponse<ProductDTO>> {

    private final Mediator mediator;
    private final ProductRepository repository;

    private List<ProductDTO> getAllProductDTOs() {
        val products = repository.findAll();
        return new ProductMapper().listOfEntitiesToListOfDTOs(products);
    }

    @Override
    public DTOListResponse<ProductDTO> handle(
            @NotNull EmptyRequest<DTOListResponse<ProductDTO>>
                    dtoListResponseEmptyRequest) {

        val foundProductDTOs =
                getAllProductDTOs();
        val responseProducts =
                new DTOListResponse<ProductDTO>(foundProductDTOs);

        this.mediator.emit(responseProducts);
        return responseProducts;
    }
}
