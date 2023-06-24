package rs.ac.bg.fon.silab.diplomskirad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.silab.diplomskirad.domain.Product;
import rs.ac.bg.fon.silab.diplomskirad.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:name%")
    List<Product> findAllByNameOrSimilar(@Param("name") String name);


    List<Product> findByProductName(String productName);
}
