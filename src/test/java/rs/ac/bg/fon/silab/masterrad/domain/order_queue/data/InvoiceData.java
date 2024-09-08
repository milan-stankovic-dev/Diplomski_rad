package rs.ac.bg.fon.silab.masterrad.domain.order_queue.data;

import org.example.dto.OrderRequest;
import org.example.dto.OrderResponse;
import rs.ac.bg.fon.silab.masterrad.domain.order_queue.OrderQueue;
import rs.ac.bg.fon.silab.masterrad.domain.partner.Partner;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.singletonList;

public interface InvoiceData {
    String WELL_FORMED_VALID_XML_DATA = "<invoice><issue_date>2024-04-14</issue_date><due_date>2024-07-14T00:00:00</due_date><ordering_from>Zmaj</ordering_from><total_cost>12419.80</total_cost><Invoice_items><item><order_amount>20</order_\n" +
            "amount><product_name>Koka-Kola</product_name></item><item><order_amount>20</order_amount><product_name>Lopta</product_name></item><item><order_amount>20</order_amount><product_name>Voda voda</product_nam\n" +
            "e></item></Invoice_items></invoice>";
    String WELL_FORMED_VALID_XML_DATA_2 = "<invoice><issue_date>2024-09-03</issue_date><due_date>2024-12-03T00:00:00</due_date><ordering_from>Leptir D.O.O</ordering_from><total_cost>2.00</total_cost><Invoice_items>" +
            "<item><order_amount>1</order_amount><product_name>Name</product_name></item><item><order_amount>1</order_amount><product_name>ProductNamee</product_name></item></Invoice_items></invoice>";
    Partner WELL_FORMED_SUPPLIER = new Partner(
            1L, "exampleAddress", "exampleName");
    OrderQueue WELL_FORMED_UNPROCESSED_INVOICE = new OrderQueue(
        1L, WELL_FORMED_VALID_XML_DATA, LocalDate.now(), false, WELL_FORMED_SUPPLIER);
    OrderQueue WELL_FORMED_UNPROCESSED_INVOICE_2 = new OrderQueue(
            2L, WELL_FORMED_VALID_XML_DATA_2, LocalDate.now(), false, WELL_FORMED_SUPPLIER);
    List<OrderQueue> WELL_FORMED_INVOICE_LIST_2_LONG = List.of(WELL_FORMED_UNPROCESSED_INVOICE,
            WELL_FORMED_UNPROCESSED_INVOICE_2);
    List<OrderQueue> SINGLE_INVOICE_LIST = singletonList(WELL_FORMED_UNPROCESSED_INVOICE);
    OrderResponse WELL_FORMED_AFFIRMING_RESPONSE = new OrderResponse(
            1L, WELL_FORMED_VALID_XML_DATA, true, true);
    OrderResponse WELL_FORMED_AFFIRMING_RESPONSE_2 = new OrderResponse(
            2L, WELL_FORMED_VALID_XML_DATA_2, true, true);
    OrderResponse WELL_FORMED_DENYING_RESPONSE = new OrderResponse(
            1L, WELL_FORMED_VALID_XML_DATA, false, false);
    OrderRequest WELL_FORMED_ORDER_REQUEST = new OrderRequest(
            1L, WELL_FORMED_VALID_XML_DATA);
    OrderRequest WELL_FORMED_ORDER_REQUEST_2 = new OrderRequest(
            2L, WELL_FORMED_VALID_XML_DATA_2);
}
