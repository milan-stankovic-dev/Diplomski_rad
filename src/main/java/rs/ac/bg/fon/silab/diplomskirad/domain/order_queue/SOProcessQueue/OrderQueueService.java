package rs.ac.bg.fon.silab.diplomskirad.domain.order_queue.SOProcessQueue;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.val;
import org.example.constants.XMLConstants;
import org.example.dto.OrderRequest;
import org.example.dto.OrderResponse;
import org.example.util.XMLParser;
import org.example.validator.XMLValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;
import rs.ac.bg.fon.silab.diplomskirad.domain.order_queue.OrderQueue;
import rs.ac.bg.fon.silab.diplomskirad.repository.OrderQueueRepository;
import rs.ac.bg.fon.silab.diplomskirad.repository.ProductRepository;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log
public class OrderQueueService {
    private final OrderQueueRepository repository;
    private final RestTemplate restTemplate;
    private final ProductRepository productRepository;
    @Scheduled(fixedRate = 60000)
    @Transactional
    public void processInvoices()
            throws ParserConfigurationException, IOException, SAXException {

        log.info("STARTING TO PROCESS...");
        final List<OrderQueue> invoices = repository.findByProcessedFalse();

        if(invoices.isEmpty()) {
            log.info("NO INVOICES FOUND. SKIPPING.");
            return;
        }

        validateAllXMLFully(orderQueuesToXMLs(invoices),
                XMLConstants.invoiceSchema);

        log.info("INVOICES FOUND: " + invoices);
        final List<OrderRequest> requests = dbEntriesToRequests(invoices);
        log.info("SENDING REQUESTS: " + requests);

        for(val request: requests){
            processSingleRequest(request);
        }

        log.info("PROCESSED.");
    }

    private void validateAllXMLFully(List<String> xmlDocs, String xmlSchema)
            throws ParserConfigurationException, IOException, SAXException {

        val validator = XMLValidator.getInstance();

        for(val xml : xmlDocs) {
            validator.validateXMLFully(xml,xmlSchema);
        }
    }

    private List<String> orderQueuesToXMLs(List<OrderQueue> queues) {
        return queues.stream()
                .map(OrderQueue::getXmlData)
                .toList();
    }

    @Transactional
    private List<OrderRequest> dbEntriesToRequests(List<OrderQueue> dbEntries) {
        return dbEntries.stream().map(invoice ->
                new OrderRequest(invoice.getId(), invoice.getXmlData())).toList();
    }

    @Transactional
    private void processSingleRequest(OrderRequest request)
            throws ParserConfigurationException, IOException, SAXException {

        log.info("SENDING REQUESTS: " + request);
        final ResponseEntity<OrderResponse> serverResponseEntity = restTemplate.postForEntity(
                "http://localhost:6969/invoice/process", request,
                OrderResponse.class
        );
        log.info("SERVER RESPONSE: " + serverResponseEntity.getBody());
        updateRelevantInDb(serverResponseEntity);
    }

    @Transactional
    private void updateRelevantInDb(@NotNull ResponseEntity<OrderResponse> serverResponse)
            throws ParserConfigurationException, IOException, SAXException {

        val body = serverResponse.getBody();

        validateOrderResponse(body);

        val parser = XMLParser.getInstance();
        val backLog = parser.parseAllNamed(body.XMLContents(), "item");
        adjustAllStocks(backLog);
        setInvoiceToProcessed(body.id());
    }

    private void validateOrderResponse(OrderResponse response) {
        if(response == null) {
            log.info("Server response malformed.");
            throw new IllegalStateException("Malformed server response.");
        }

        if(!response.successful()) {
            log.info("Unsuccessful order!");
            throw new IllegalStateException("Server was not able to send products.");
        }
    }
    @Transactional
    private void setInvoiceToProcessed(Long id) {
        repository.setProcessedByIdTo(id, true);
        log.info("ID WAS " + id);
        log.info("UPDATED INVOICE TO PROCESSED IN DB.");
    }

    @Transactional
    private void adjustAllStocks(Map<String, Integer> backlog) {
        backlog.forEach(productRepository::increaseStockByName);
        log.info("ADJUSTED ALL STOCKS.");
    }

}
