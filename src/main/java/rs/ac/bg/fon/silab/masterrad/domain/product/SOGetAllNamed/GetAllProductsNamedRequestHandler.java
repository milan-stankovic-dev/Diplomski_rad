package rs.ac.bg.fon.silab.masterrad.domain.product.SOGetAllNamed;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.masterrad.domain.product.Product;
import rs.ac.bg.fon.silab.masterrad.domain.product.ProductNamedRequest;
import rs.ac.bg.fon.silab.masterrad.dto.ProductDTO;
import rs.ac.bg.fon.silab.masterrad.mapper.ProductMapper;
import rs.ac.bg.fon.silab.masterrad.repository.ProductRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllProductsNamedRequestHandler
        implements RequestHandler<ProductNamedRequest, DTOListResponse<ProductDTO>> {

    final Mediator mediator;
    final ProductRepository repository;
    final ProductMapper mapper;

    private List<ProductDTO> getAllProductDTOsWithNameOrSimilar(String name) {
        final List<Product> foundProducts = repository
                .findAllByNameOrSimilar(name);

        return mapper.listOfEntitiesToListOfDTOs(foundProducts);
    }

    @Override
    public DTOListResponse<ProductDTO> handle(@NotNull ProductNamedRequest
                                                          productNamedRequest) {
        val productsWithSaidName =
                getAllProductDTOsWithNameOrSimilar(productNamedRequest.name());
        val productResponse = new DTOListResponse<ProductDTO>
                (productsWithSaidName);

        this.mediator.emit(productResponse);

        return productResponse;
    }
}

