package rs.ac.bg.fon.silab.diplomskirad.domain.order_queue.SOProcessQueue;

import jakarta.validation.constraints.Max;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.val;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rs.ac.bg.fon.silab.diplomskirad.domain.invoice.Invoice;
import rs.ac.bg.fon.silab.diplomskirad.domain.order_queue.InvoiceRequest;
import rs.ac.bg.fon.silab.diplomskirad.domain.order_queue.OrderQueue;
import rs.ac.bg.fon.silab.diplomskirad.repository.OrderQueueRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class OrderQueueService {
    private final OrderQueueRepository repository;
    private final RestTemplate restTemplate;

    @Scheduled(fixedRate = 10000)
    public void processInvoices() {
        log.info("STARTING TO PROCESS...");
        final List<OrderQueue> invoices = repository.findByProcessedFalse();

        log.info("INVOICES FOUND: " + invoices);

        final InvoiceRequest request = new InvoiceRequest(
          invoices.stream().map(OrderQueue::getXmlData).toList());

        log.info("SENDING REQUESTS: " + request);
        val serverResponseEntity = restTemplate.postForEntity(
                "http://localhost:6969/invoice/process", request,
                Object.class
        );
        log.info("SERVER RESPONSE: " + serverResponseEntity.getBody());
        log.info("PROCESSED...");
    }
}
