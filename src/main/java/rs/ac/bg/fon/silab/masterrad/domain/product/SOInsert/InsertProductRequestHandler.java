package rs.ac.bg.fon.silab.masterrad.domain.product.SOInsert;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.domain.partner.Partner;
import rs.ac.bg.fon.silab.masterrad.domain.product.Product;
import rs.ac.bg.fon.silab.masterrad.dto.ProductDTO;
import rs.ac.bg.fon.silab.masterrad.exception.ExistingEntityException;
import rs.ac.bg.fon.silab.masterrad.mapper.ProductMapper;
import rs.ac.bg.fon.silab.masterrad.repository.PartnerRepository;
import rs.ac.bg.fon.silab.masterrad.repository.ProductRepository;

import java.lang.module.FindException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InsertProductRequestHandler
        implements RequestHandler<ProductDTO, ProductDTO> {

    private final Mediator mediator;
    private final ProductRepository repository;
    private final ProductMapper productMapper;
    private final PartnerRepository partnerRepository;

    private ProductDTO insertProduct(ProductDTO productDTO) {
        List<Product> discoveredProduct = repository
                .findByProductName(productDTO.productName());

        if(!discoveredProduct.isEmpty()){
            throw new ExistingEntityException("Entity already exists");
        }

        final Partner supplierById =
                partnerRepository.findById(productDTO.supplierId())
                        .orElseThrow(()-> new FindException("Supplier with said id" +
                                " does not exist"));

        val product = productMapper.dTOtoEntity(productDTO);
        product.setSupplier(supplierById);
        return productMapper.entityToDTO(repository.save(product));
    }
    @Override
    public ProductDTO handle(@NotNull ProductDTO productDTO) {
        val insertedProduct = insertProduct(productDTO);
        this.mediator.emit(insertedProduct);

        return insertedProduct;
    }
}
