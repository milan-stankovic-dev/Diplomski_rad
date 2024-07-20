package rs.ac.bg.fon.silab.masterrad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.silab.masterrad.domain.product.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:name%")
    List<Product> findAllByNameOrSimilar(@Param("name") String name);


    List<Product> findByProductName(String productName);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.currentStock = p.currentStock - :stock WHERE p.productName = :name")
    void decreaseStockByName(String name, Integer stock);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.currentStock = p.currentStock + :stock WHERE p.productName = :name")
    void increaseStockByName(String name, Integer stock);
}
