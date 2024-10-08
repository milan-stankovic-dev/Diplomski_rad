package rs.ac.bg.fon.silab.masterrad.mapper;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.domain.partner.Partner;
import rs.ac.bg.fon.silab.masterrad.domain.product.Product;
import rs.ac.bg.fon.silab.masterrad.dto.ProductDTO;

import java.util.UUID;

@Component
public non-sealed class ProductMapper implements DtoDomainMapper<ProductDTO, Product> {
    @Override
    public ProductDTO entityToDTO(Product product) {
        val productDto = new ProductDTO(
                product.getId(),
                product.getProductName(),
                product.getWeight(),
                product.isFragile(),
                product.getCurrentStock(),
                product.getMinimalStock(),
                product.getOrderAmount(),
                product.getType(),
                product.getPrice(),
                product.getSupplier() == null? 0 :
                product.getSupplier().getId(),
                product.getCode());

        return productDto;
    }

    @Override
    public Product dTOtoEntity(ProductDTO productDTO) {
        val product = new Product(
                productDTO.id(),
                productDTO.productName(),
                productDTO.weight(),
                productDTO.fragile(),
                productDTO.currentStock(),
                productDTO.minimalStock(),
                productDTO.orderAmount(),
                productDTO.type(),
                productDTO.price(),
                Partner
                        .builder()
                        .id(productDTO.supplierId())
                        .build(),
                productDTO.code() == null ? UUID.randomUUID() :
                        productDTO.code());

        return product;
    }
}
