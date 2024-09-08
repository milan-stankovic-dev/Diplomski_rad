package rs.ac.bg.fon.silab.masterrad.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import rs.ac.bg.fon.silab.masterrad.domain.order_queue.OrderQueue;
import rs.ac.bg.fon.silab.masterrad.domain.partner.Partner;
import rs.ac.bg.fon.silab.masterrad.domain.product.Product;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static rs.ac.bg.fon.silab.masterrad.domain.order_queue.data.OrderQueueData.WELL_FORMED_ORDER_QUEUE_PROCESSED;
import static rs.ac.bg.fon.silab.masterrad.domain.order_queue.data.OrderQueueData.WELL_FORMED_ORDER_QUEUE_UNPROCESSED;
import static rs.ac.bg.fon.silab.masterrad.domain.order_queue.data.ProductData.*;

@DataJpaTest
@Commit
public class OrderQueueRepositoryTest {
    @Autowired
    private OrderQueueRepository repository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PartnerRepository partnerRepository;

    private Partner savedPartner;
    private Product savedProduct;
    private OrderQueue savedOrderQueue;
    
    @BeforeEach
    void setUp() {
        savedPartner = partnerRepository.saveAndFlush(WELL_FORMED_SUPPLIER_1);
        WELL_FORMED_PRODUCT_1.setSupplier(savedPartner);
        savedProduct = productRepository.saveAndFlush(WELL_FORMED_PRODUCT_1);
        WELL_FORMED_ORDER_QUEUE_PROCESSED.setSupplier(savedPartner);
        WELL_FORMED_ORDER_QUEUE_UNPROCESSED.setSupplier(savedPartner);
        savedOrderQueue = repository.saveAndFlush(WELL_FORMED_ORDER_QUEUE_UNPROCESSED);
        savedOrderQueue = repository.saveAndFlush(WELL_FORMED_ORDER_QUEUE_PROCESSED);
    }
    
    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
        partnerRepository.deleteAll();
        repository.deleteAll();
    }

    @Test
    @DisplayName("Get order queues by processed false test")
    void getByProcessedFalseTest() {
        final List<OrderQueue> unprocessedInvoices =
                repository.findByProcessedFalse();

        assertThat(unprocessedInvoices)
                .isNotEmpty()
                .hasSize(1)
                .containsExactlyInAnyOrder(
                        WELL_FORMED_ORDER_QUEUE_UNPROCESSED);
    }
}
