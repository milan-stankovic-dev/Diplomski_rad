package rs.ac.bg.fon.silab.masterrad.domain.product;

import io.jkratz.mediator.core.Mediator;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.silab.masterrad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.masterrad.domain.DeleteCommand;
import rs.ac.bg.fon.silab.masterrad.dto.ProductDTO;
import rs.ac.bg.fon.silab.masterrad.dto.ProductFullDTO;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    private final Mediator mediator;
    @GetMapping("/all")
    public ResponseEntity<List<ProductFullDTO>> getAllProducts() {
        final DTOListResponse<ProductFullDTO> productsResponse =
            this.mediator.dispatch(new GetProductsRequest());

        return ResponseEntity.ok(productsResponse.dtos());
    }

    @GetMapping("/all/named/{name}")
    public ResponseEntity<List<ProductDTO>> getAllProductsWithName(
            @PathVariable String name){

        val productServiceResponse =
                mediator.dispatch(new ProductNamedRequest(name));

        return ResponseEntity.ok(productServiceResponse.dtos());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insertProduct(@RequestBody
                                                ProductDTO productDTO) {
       val insertedProduct = mediator.dispatch(productDTO);
       return ResponseEntity.ok(insertedProduct);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO,
                                                    @PathVariable long id){
        val updatedProduct = mediator.dispatch(
                new UpdateProductRequest(productDTO, id));
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id){
        this.mediator.dispatch(new DeleteCommand(id));
        return ResponseEntity.noContent().build();
    }

}

