package rs.ac.bg.fon.silab.diplomskirad.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.domain.Product;
import rs.ac.bg.fon.silab.diplomskirad.dto.ProductDTO;
import rs.ac.bg.fon.silab.diplomskirad.exception.InvalidIdException;
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

    private Product findProductByID(long id){
        Optional<Product> foundProduct = repository.findById(id);
        if(foundProduct.isEmpty()){
            throw new EntityNotFoundException("No product found with said id.");
        }
        return foundProduct.get();
    }
    public List<ProductDTO> getAllProductDTOsWithNameOrSimilar(String name) {
        List<Product> foundProducts = repository.findAllByNameOrSimilar(name);
        return new ProductMapper().listOfEntitiesToListOfDTOs(foundProducts);
    }

    public ProductDTO updateProduct(ProductDTO productDTO, long id) {
        if(productDTO == null){
            throw new NullPointerException("Please provide a product for update.");
        }

        if(id < 0){
            throw new InvalidIdException("Provided id not valid.");
        }

        Product foundProduct = findProductByID(id);
        Product updatedProduct = new ProductMapper().dTOtoEntity(productDTO);
        updatedProduct.setId(id);

        return new ProductMapper().entityToDTO(repository.save(updatedProduct));
    }

    public void deleteProductById(long id) {
        repository.deleteById(id);
    }
}
