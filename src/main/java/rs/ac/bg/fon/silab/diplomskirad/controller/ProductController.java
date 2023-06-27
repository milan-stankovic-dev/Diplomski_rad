package rs.ac.bg.fon.silab.diplomskirad.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.silab.diplomskirad.domain.Product;
import rs.ac.bg.fon.silab.diplomskirad.dto.ProductDTO;
import rs.ac.bg.fon.silab.diplomskirad.exception.ExistingEntityException;
import rs.ac.bg.fon.silab.diplomskirad.service.ProductService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {
    private final ProductService service;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProductDTOs());
    }

    @GetMapping("/all/named/{name}")
    public ResponseEntity<List<ProductDTO>> getAllProductsWithName(@PathVariable String name){
        var productServiceResponse = service.getAllProductDTOsWithNameOrSimilar(name);

        if(productServiceResponse.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productServiceResponse.get());
    }

    @PostMapping
    public ResponseEntity<Object> insertProduct(@RequestBody
                                        ProductDTO productDTO) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.insertProduct(productDTO));
        }catch (ExistingEntityException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        }catch (TransactionSystemException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage() + ". Check your input values again.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO,
                                                @PathVariable long id){
        var productServiceResponse = service.updateProduct(productDTO,id);
        return ResponseEntity.of(productServiceResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id){
       try{
           service.deleteProductById(id);
           return ResponseEntity.noContent().build();
       }catch (EntityNotFoundException ex){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
    }

}
