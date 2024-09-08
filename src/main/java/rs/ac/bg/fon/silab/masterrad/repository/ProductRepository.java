package rs.ac.bg.fon.silab.masterrad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.silab.masterrad.domain.product.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:name%")
    List<Product> findAllByNameOrSimilar(@Param("name") String name);


    List<Product> findByProductName(String productName);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.currentStock = p.currentStock + :stock WHERE p.code = :code")
    void increaseStockByCode(UUID code, Integer stock);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.currentStock = p.currentStock + :stock WHERE p.productName = :name")
    void increaseStockByName(String name, Integer stock);

    Optional<Product> findByCode(UUID code);
}
