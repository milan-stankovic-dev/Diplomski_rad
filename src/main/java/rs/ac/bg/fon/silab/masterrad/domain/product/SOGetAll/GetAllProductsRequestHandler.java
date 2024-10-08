package rs.ac.bg.fon.silab.masterrad.domain.product.SOGetAll;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.masterrad.domain.product.GetProductsRequest;
import rs.ac.bg.fon.silab.masterrad.dto.ProductDTO;
import rs.ac.bg.fon.silab.masterrad.dto.ProductFullDTO;
import rs.ac.bg.fon.silab.masterrad.mapper.PartnerMapper;
import rs.ac.bg.fon.silab.masterrad.mapper.ProductFullMapper;
import rs.ac.bg.fon.silab.masterrad.mapper.ProductMapper;
import rs.ac.bg.fon.silab.masterrad.repository.ProductRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllProductsRequestHandler
        implements RequestHandler<GetProductsRequest,
        DTOListResponse<ProductFullDTO>> {

    private final Mediator mediator;
    private final ProductRepository repository;
    private final ProductFullMapper mapper;

    private List<ProductFullDTO> getAllProductDTOs() {
        val products = repository.findAll();
        return mapper.listOfEntitiesToListOfDTOs(products);
    }
    @Override
    public DTOListResponse<ProductFullDTO> handle(@NotNull GetProductsRequest
                                                          getProductsRequest) {
        val foundProductDTOs =
                getAllProductDTOs();
        val responseProducts =
                new DTOListResponse<>(foundProductDTOs);

        System.out.println("****** FOUND PRODUCTS ******");
        foundProductDTOs.forEach(System.out::println);
        this.mediator.emit(responseProducts);
        return responseProducts;
    }
}
