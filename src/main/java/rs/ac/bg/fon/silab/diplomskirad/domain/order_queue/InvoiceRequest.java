package rs.ac.bg.fon.silab.diplomskirad.domain.order_queue;

import java.util.List;

public record InvoiceRequest(
    List<String> XMLContent
) { }
