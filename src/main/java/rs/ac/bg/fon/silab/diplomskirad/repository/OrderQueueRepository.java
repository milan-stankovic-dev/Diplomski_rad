package rs.ac.bg.fon.silab.diplomskirad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.silab.diplomskirad.domain.order_queue.OrderQueue;
import rs.ac.bg.fon.silab.diplomskirad.domain.order_queue.SOProcessQueue.OrderQueueService;

import java.util.List;

public interface OrderQueueRepository extends JpaRepository<OrderQueue, Long> {
    List<OrderQueue> findByProcessedFalse();
}
