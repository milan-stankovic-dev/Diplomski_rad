package rs.ac.bg.fon.silab.diplomskirad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.silab.diplomskirad.domain.order_queue.OrderQueue;
import rs.ac.bg.fon.silab.diplomskirad.domain.order_queue.SOProcessQueue.OrderQueueService;

import java.util.List;

public interface OrderQueueRepository extends JpaRepository<OrderQueue, Long> {
    List<OrderQueue> findByProcessedFalse();

    @Transactional
    @Modifying
    @Query("UPDATE OrderQueue o SET o.processed = :processedVal WHERE o.id = :oqId")
    void setProcessedByIdTo(Long oqId, boolean processedVal);
}
