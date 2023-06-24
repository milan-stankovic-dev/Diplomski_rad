package rs.ac.bg.fon.silab.diplomskirad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.silab.diplomskirad.domain.Product;
import rs.ac.bg.fon.silab.diplomskirad.dto.ProductDTO;
import rs.ac.bg.fon.silab.diplomskirad.service.ProductService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {
    private final ProductService service;

    @GetMapping("/all")
    public List<ProductDTO> getAllProducts() {
        return service.getAllProductDTOs();
    }

    @PostMapping
    public ProductDTO insertProduct(@RequestBody ProductDTO productDTO) {
        return service.insertProduct(productDTO);
    }

    @GetMapping("/all/named/{name}")
    public List<ProductDTO> getAllProductsWithName(@PathVariable String name){
        return service.getAllProductDTOsWithNameOrSimilar(name);
    }

    @PutMapping("/update/{id}")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO,
                                    @PathVariable long id){
        return service.updateProduct(productDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id){
        service.deleteProductById(id);
    }
}
