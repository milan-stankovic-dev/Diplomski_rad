package rs.ac.bg.fon.silab.masterrad.domain.order_queue.data;

import rs.ac.bg.fon.silab.masterrad.domain.order_queue.OrderQueue;

import java.time.LocalDate;

import static rs.ac.bg.fon.silab.masterrad.domain.order_queue.data.InvoiceData.*;

public interface OrderQueueData {
    OrderQueue WELL_FORMED_ORDER_QUEUE_UNPROCESSED = new OrderQueue(
            null, WELL_FORMED_VALID_XML_DATA, LocalDate.now(),
            false, WELL_FORMED_SUPPLIER);
    OrderQueue WELL_FORMED_ORDER_QUEUE_PROCESSED = new OrderQueue(
            null, WELL_FORMED_VALID_XML_DATA_2, LocalDate.now(),
            true, WELL_FORMED_SUPPLIER);
}
