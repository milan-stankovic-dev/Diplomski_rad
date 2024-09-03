package rs.ac.bg.fon.silab.masterrad.mapper;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.domain.partner.Partner;
import rs.ac.bg.fon.silab.masterrad.domain.product.Product;
import rs.ac.bg.fon.silab.masterrad.dto.ProductFullDTO;

@Component
@RequiredArgsConstructor
public final class ProductFullMapper implements DtoDomainMapper<ProductFullDTO, Product> {
    private final PartnerMapper partnerMapper;

    @Override
    public ProductFullDTO entityToDTO(Product product) {
        val productDto = new ProductFullDTO(
                product.getId(),
                product.getProductName(),
                product.getWeight(),
                product.isFragile(),
                product.getCurrentStock(),
                product.getMinimalStock(),
                product.getOrderAmount(),
                product.getType(),
                product.getPrice(),
                product.getSupplier() == null ? null :
                partnerMapper.entityToDTO(product.getSupplier()),
                product.getCode());
        return productDto;
    }

    @Override
    public Product dTOtoEntity(ProductFullDTO productDTO) {
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
                        .id(productDTO.id())
                        .build(),
                productDTO.code());

        return product;
    }
}
