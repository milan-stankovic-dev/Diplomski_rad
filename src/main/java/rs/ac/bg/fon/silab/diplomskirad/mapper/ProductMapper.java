package rs.ac.bg.fon.silab.diplomskirad.mapper;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.Product;
import rs.ac.bg.fon.silab.diplomskirad.dto.ProductDTO;
import rs.ac.bg.fon.silab.diplomskirad.mapper.DtoDomainMapper;
@Component
public non-sealed class ProductMapper implements DtoDomainMapper<ProductDTO, Product> {

    @Override
    public ProductDTO entityToDTO(Product product) {
        var productDto = new ProductDTO(
                product.getId(),
                product.getProductName(),
                product.getWeight(),
                product.isFragile(),
                product.getCurrentStock(),
                product.getType(),
                product.getPrice()
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
                0,
                1,
                productDTO.type(),
                productDTO.price(),
                null
        );

        return product;
    }
}
