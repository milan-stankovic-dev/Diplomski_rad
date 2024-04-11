package rs.ac.bg.fon.silab.diplomskirad.domain.order_queue.SOProcessQueue;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.val;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.repository.OrderQueueRepository;

@Service
@RequiredArgsConstructor
@Log
public class OrderQueueService {
    private final OrderQueueRepository repository;

    @Scheduled(fixedRate = 10000)
    public void processInvoices() {
        log.info("STARTING TO PROCESS...");
        val invoices = repository.findByProcessedFalse();
        invoices.forEach(invoice -> log.info("UNPROCESSED: " + invoice.getXmlData()));
        log.info("PROCESSED...");
    }
}
