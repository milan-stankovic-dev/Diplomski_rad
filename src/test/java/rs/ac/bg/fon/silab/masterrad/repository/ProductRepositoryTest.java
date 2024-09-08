package rs.ac.bg.fon.silab.masterrad.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import rs.ac.bg.fon.silab.masterrad.domain.partner.Partner;
import rs.ac.bg.fon.silab.masterrad.domain.product.Product;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static rs.ac.bg.fon.silab.masterrad.domain.order_queue.data.ProductData.WELL_FORMED_PRODUCT_1;
import static rs.ac.bg.fon.silab.masterrad.domain.order_queue.data.ProductData.WELL_FORMED_SUPPLIER_1;

@DataJpaTest
@Commit
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PartnerRepository partnerRepository;
    private Partner savedPartner;
    private Product savedProduct;
    private Product increasedProduct;
    @Autowired
    private EntityManager em;

    final UUID code = UUID.fromString(
            "123e4567-e89b-12d3-a456-426614174000");
    final int increaseBy = 10;

    @BeforeEach
    void setUp() {
        savedPartner = partnerRepository.saveAndFlush(WELL_FORMED_SUPPLIER_1);
        WELL_FORMED_PRODUCT_1.setSupplier(savedPartner);
        savedProduct = productRepository.saveAndFlush(WELL_FORMED_PRODUCT_1);
        productRepository.increaseStockByCode(code, increaseBy);
        em.flush();
        em.clear();
        increasedProduct = productRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Product not found by code."));
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
        partnerRepository.deleteAll();
    }

    @Test
    void increaseStockByCodeTest() {
        assertThat(increasedProduct.getCurrentStock())
                .isEqualTo(110);
    }

    @Test
    void findByCodeTest() {
        assertEquals(savedProduct.getId(), increasedProduct.getId());
    }

    @Test
    void findByNameOrSimilarTest() {
        final List<Product> foundProducts = productRepository
                .findAllByNameOrSimilar("example");

        assertThat(foundProducts)
                .containsExactlyInAnyOrder(savedProduct);
    }

    @Test
    void findByNameOrSimilarNotFoundTest() {
        final List<Product> foundProducts = productRepository
                .findAllByNameOrSimilar("foo");

        assertThat(foundProducts)
                .isEmpty();
    }

    @Test
    void findByProductNameTest() {
        final List<Product> foundProducts = productRepository
                .findByProductName("exampleProductName");

        assertThat(foundProducts)
                .containsExactlyInAnyOrder(savedProduct);
    }

    @Test
    void findByProductNameNotFoundTest() {
        final List<Product> foundProducts = productRepository
                .findByProductName("foo");

        assertThat(foundProducts)
                .isEmpty();
    }
}
