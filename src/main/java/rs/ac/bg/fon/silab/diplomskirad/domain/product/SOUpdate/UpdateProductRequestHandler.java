package rs.ac.bg.fon.silab.diplomskirad.domain.product.SOUpdate;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.product.UpdateProductRequest;
import rs.ac.bg.fon.silab.diplomskirad.dto.ProductDTO;
import rs.ac.bg.fon.silab.diplomskirad.mapper.ProductMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.ProductRepository;

@Component
@RequiredArgsConstructor
public class UpdateProductRequestHandler
        implements RequestHandler<UpdateProductRequest, ProductDTO> {

    private final Mediator mediator;
    private final ProductRepository repository;
    private final ProductMapper productMapper;
    @Setter
    private Long id;
    private ProductDTO updateProduct(ProductDTO productDTO, long id) {
        val foundProduct = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no such product"));

        val updatedProduct = productMapper.dTOtoEntity(productDTO);
        updatedProduct.setId(id);

        return productMapper.entityToDTO(repository.save(updatedProduct));
    }

    @Override
    public ProductDTO handle(@NotNull UpdateProductRequest request) {
        val updatedProduct =
                updateProduct(request.productDTO(), this.id);

        this.mediator.emit(updatedProduct);
        return updatedProduct;
    }
}
