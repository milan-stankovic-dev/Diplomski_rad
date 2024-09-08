package rs.ac.bg.fon.silab.masterrad.domain.order_queue.SOProcessOrderQueue;

import org.example.dto.OrderRequest;
import org.example.dto.OrderResponse;
import org.example.util.XMLParser;
import org.example.validator.XMLValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;
import rs.ac.bg.fon.silab.masterrad.domain.order_queue.SOProcessQueue.OrderQueueService;
import rs.ac.bg.fon.silab.masterrad.repository.OrderQueueRepository;
import rs.ac.bg.fon.silab.masterrad.repository.OrderQueueRepositoryTest;
import rs.ac.bg.fon.silab.masterrad.repository.ProductRepository;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.constants.XMLConstants.INVOICE_SCHEMA;
import static org.mockito.Mockito.*;
import static rs.ac.bg.fon.silab.masterrad.domain.order_queue.data.InvoiceData.*;

@SpringBootTest
public class OrderQueueServiceTest {
    @MockBean
    private OrderQueueRepository repository;
    @MockBean
    private RestTemplate restTemplate;
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private XMLValidator xmlValidator;
    @MockBean
    private XMLParser xmlParser;
    @Autowired
    private OrderQueueService service;

    @Test
    @DisplayName("Process invoices empty test")
    void processInvoicesEmptyTest() throws ParserConfigurationException, IOException, SAXException {
        when(repository.findByProcessedFalse())
                .thenReturn(new ArrayList<>());

        service.processInvoices();

        verify(xmlValidator, never()).validateXMLFully(
                any(String.class),
                eq(INVOICE_SCHEMA));
        verify(restTemplate, never()).postForEntity(any(String.class),
                any(OrderRequest.class), any(Class.class));
        verify(xmlParser, never()).parseAllItems(any(String.class));
        verify(repository, never()).setProcessedByIdTo(any(Long.class), any(Boolean.class));
        verify(productRepository, never()).increaseStockByCode(any(UUID.class), any(Integer.class));
    }

    @Test
    @DisplayName("Process invoices one invoice test")
    void processInvoicesFoundOneTest() throws ParserConfigurationException, IOException, SAXException {
        when(repository.findByProcessedFalse())
                .thenReturn(SINGLE_INVOICE_LIST);

        doNothing().when(xmlValidator).validateXMLFully(
                WELL_FORMED_VALID_XML_DATA, INVOICE_SCHEMA);
        when(restTemplate.postForEntity("http://localhost:6969/invoice/process",
                WELL_FORMED_ORDER_REQUEST, OrderResponse.class))
                .thenReturn(ResponseEntity.ok(WELL_FORMED_AFFIRMING_RESPONSE));
        when(xmlParser.parseAllItems(WELL_FORMED_VALID_XML_DATA))
                .thenReturn(new HashMap<>(){{
                    put(UUID.fromString(
                            "53fb6515-bb6e-4408-a4da-ccdedaae7938"), 10);
                    put(UUID.fromString(
                            "53fb6515-bb6e-4408-a4da-ccdedaae7328"), 12);
                }});

        service.processInvoices();

        verify(repository, times(1))
                .setProcessedByIdTo(any(Long.class), any(Boolean.class));
        verify(productRepository, times(2)).increaseStockByCode(
                any(UUID.class), any(Integer.class));
    }

    @Test
    @DisplayName("Process invoices multiple invoice test")
    void processInvoicesFoundMultipleTest() throws ParserConfigurationException, IOException, SAXException {
        when(repository.findByProcessedFalse())
                .thenReturn(SINGLE_INVOICE_LIST);

        doNothing().when(xmlValidator).validateXMLFully(
                WELL_FORMED_VALID_XML_DATA, INVOICE_SCHEMA);
        when(restTemplate.postForEntity(
                eq("http://localhost:6969/invoice/process"),
                any(OrderRequest.class),
                eq(OrderResponse.class)))
        .thenAnswer(invocation -> {
            final OrderRequest request = invocation.getArgument(1);
            return switch (request.id().intValue()) {
                     case 1 -> ResponseEntity.ok(WELL_FORMED_AFFIRMING_RESPONSE);
                     case 2 -> ResponseEntity.ok(WELL_FORMED_AFFIRMING_RESPONSE_2);
                default -> throw new IllegalArgumentException("Wrong id passed for order request.");
            };
        });

        when(xmlParser.parseAllItems(any(String.class)))
                .thenAnswer(invocation -> {
                    final String xmlData = invocation.getArgument(0);
                    return switch (xmlData) {
                        case WELL_FORMED_VALID_XML_DATA -> new HashMap<>(){{
                            put(UUID.fromString(
                            "53fb6515-bb6e-4408-a4da-ccdedaae7938"), 10);
                            put(UUID.fromString(
                            "53fb6515-bb6e-4408-a4da-ccdedaae7328"), 12);
                        }};
                        case WELL_FORMED_VALID_XML_DATA_2 -> new HashMap<>() {{
                            put(UUID.fromString(
                                    "ae484015-ee50-4408-a4da-ae370a1a6836"), 9);
                            put(UUID.fromString(
                                    "53fb6515-bb6e-4408-a4da-ccdedaae7328"), 11);
                        }};
                        default -> throw new IllegalArgumentException("Unexpected xml data passed.");
                    };
                });

        service.processInvoices();

        verify(repository, times(1))
                .setProcessedByIdTo(any(Long.class), any(Boolean.class));
        verify(productRepository, times(2)).increaseStockByCode(
                any(UUID.class), any(Integer.class));
    }

    @Test
    @DisplayName("Process invoices empty test")
    void processInvoices() throws ParserConfigurationException, IOException, SAXException {
        when(repository.findByProcessedFalse())
                .thenReturn(SINGLE_INVOICE_LIST);

        doNothing().when(xmlValidator).validateXMLFully(
                WELL_FORMED_VALID_XML_DATA, INVOICE_SCHEMA);
        when(restTemplate.postForEntity("http://localhost:6969/invoice/process",
                WELL_FORMED_ORDER_REQUEST, OrderResponse.class))
                .thenReturn(ResponseEntity.ok(WELL_FORMED_DENYING_RESPONSE));
        when(xmlParser.parseAllItems(WELL_FORMED_VALID_XML_DATA))
                .thenReturn(new HashMap<>(){{
                    put(UUID.fromString(
                            "53fb6515-bb6e-4408-a4da-ccdedaae7938"), 10);
                    put(UUID.fromString(
                            "53fb6515-bb6e-4408-a4da-ccdedaae7328"), 12);
                }});

        assertThatThrownBy(()-> service.processInvoices())
                .isInstanceOf(IllegalStateException.class)
                        .hasMessage("Server was not able to send products.");

        verify(xmlParser, never()).parseAllItems(any(String.class));
        verify(repository, never()).setProcessedByIdTo(any(Long.class), any(Boolean.class));
        verify(productRepository, never()).increaseStockByCode(any(UUID.class), any(Integer.class));
    }
}
