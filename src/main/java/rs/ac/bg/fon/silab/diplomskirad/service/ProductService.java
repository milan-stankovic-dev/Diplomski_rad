package rs.ac.bg.fon.silab.diplomskirad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.domain.Product;
import rs.ac.bg.fon.silab.diplomskirad.dto.ProductDTO;
import rs.ac.bg.fon.silab.diplomskirad.mapper.ProductMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository repository;

    public List<ProductDTO> getAllProductDTOs() {
        var products = repository.findAll();
        return new ProductMapper().listOfEntitiesToListOfDTOs(products);
    }

    public ProductDTO insertProduct(ProductDTO productDTO) {
        try {
            var product = new ProductMapper().dTOtoEntity(productDTO);
            var savedProduct = repository.save(product);
            return new ProductMapper().entityToDTO(savedProduct);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            throw ex;
        }

    }
}
