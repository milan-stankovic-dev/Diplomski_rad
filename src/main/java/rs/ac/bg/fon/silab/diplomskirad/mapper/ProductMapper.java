package rs.ac.bg.fon.silab.diplomskirad.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.product.Product;
import rs.ac.bg.fon.silab.diplomskirad.dto.ProductDTO;

@Component
@RequiredArgsConstructor
public non-sealed class ProductMapper implements DtoDomainMapper<ProductDTO, Product> {
    private final PartnerMapper partnerMapper;
    @Override
    public ProductDTO entityToDTO(Product product) {
        var productDto = new ProductDTO(
                product.getId(),
                product.getProductName(),
                product.getWeight(),
                product.isFragile(),
                product.getCurrentStock(),
                product.getMinimalStock(),
                product.getOrderAmount(),
                product.getType(),
                product.getPrice(),
                product.getSupplier() == null? null :
                partnerMapper.entityToDTO(product.getSupplier())
        );

        return productDto;
    }

    @Override
    public Product dTOtoEntity(ProductDTO productDTO) {
        var product = new Product(
                productDTO.id(),
                productDTO.productName(),
                productDTO.weight(),
                productDTO.fragile(),
                productDTO.amount(),
                productDTO.minimalStock(),
                productDTO.orderAmount(),
                productDTO.type(),
                productDTO.price(),
                productDTO.supplier() == null? null :
                partnerMapper.dTOtoEntity(productDTO.supplier())
        );

        return product;
    }
}
