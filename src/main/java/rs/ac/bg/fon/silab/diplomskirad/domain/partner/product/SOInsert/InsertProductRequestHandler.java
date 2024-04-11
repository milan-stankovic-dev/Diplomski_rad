package rs.ac.bg.fon.silab.diplomskirad.domain.partner.product.SOInsert;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.partner.product.Product;
import rs.ac.bg.fon.silab.diplomskirad.dto.ProductDTO;
import rs.ac.bg.fon.silab.diplomskirad.exception.ExistingEntityException;
import rs.ac.bg.fon.silab.diplomskirad.mapper.ProductMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.ProductRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InsertProductRequestHandler
        implements RequestHandler<ProductDTO, ProductDTO> {

    private final Mediator mediator;
    private final ProductRepository repository;
    private final ProductMapper productMapper;

    private ProductDTO insertProduct(ProductDTO productDTO) {
        List<Product> discoveredProduct = repository
                .findByProductName(productDTO.productName());

        if(!discoveredProduct.isEmpty()){
            throw new ExistingEntityException("Entity already exists");
        }

        val product = productMapper.dTOtoEntity(productDTO);
        return productMapper.entityToDTO(repository.save(product));
    }
    @Override
    public ProductDTO handle(@NotNull ProductDTO productDTO) {
        val insertedProduct = insertProduct(productDTO);
        this.mediator.emit(insertedProduct);

        return insertedProduct;
    }
}
