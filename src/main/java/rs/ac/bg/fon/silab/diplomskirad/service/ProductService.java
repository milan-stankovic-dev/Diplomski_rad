package rs.ac.bg.fon.silab.diplomskirad.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.domain.product.Product;
import rs.ac.bg.fon.silab.diplomskirad.dto.ProductDTO;
import rs.ac.bg.fon.silab.diplomskirad.exception.ExistingEntityException;
import rs.ac.bg.fon.silab.diplomskirad.mapper.ProductMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper productMapper;

    public List<ProductDTO> getAllProductDTOs() {
        var products = repository.findAll();
        return productMapper.listOfEntitiesToListOfDTOs(products);
    }

    public ProductDTO insertProduct(ProductDTO productDTO) throws ExistingEntityException{
            List<Product> discoveredProduct = repository
                    .findByProductName(productDTO.productName());
            if(!discoveredProduct.isEmpty()){
                throw new ExistingEntityException("Entity already exists");
            }
            var product = productMapper.dTOtoEntity(productDTO);
            return productMapper.entityToDTO(repository.save(product));
    }

    public Optional<List<ProductDTO>> getAllProductDTOsWithNameOrSimilar(String name) {
        List<Product> foundProducts = repository.findAllByNameOrSimilar(name);
        if(foundProducts.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(productMapper.listOfEntitiesToListOfDTOs(foundProducts));
    }

    public Optional<ProductDTO> updateProduct(ProductDTO productDTO, long id) {
        Optional<Product> foundProductOptional = repository.findById(id);
        if(foundProductOptional.isEmpty()){
            throw new EntityNotFoundException("There is no such product");
        }
        Product updatedProduct = productMapper.dTOtoEntity(productDTO);
        updatedProduct.setId(id);

        return Optional.of(productMapper.entityToDTO(repository.save(updatedProduct)));
    }

    public void deleteProductById(long id) {
        Optional<Product> productOptional
                = repository.findById(id);
        if(productOptional.isEmpty()){
            throw new EntityNotFoundException("There is no such product");
        }
        repository.deleteById(id);
    }
}
