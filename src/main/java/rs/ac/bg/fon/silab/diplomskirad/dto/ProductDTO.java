package rs.ac.bg.fon.silab.diplomskirad.dto;

import io.jkratz.mediator.core.Event;
import io.jkratz.mediator.core.Request;
import lombok.NonNull;
import rs.ac.bg.fon.silab.diplomskirad.domain.enumeration.ProductType;

import java.math.BigDecimal;

public record ProductDTO(
    long id,
    @NonNull
    String productName,
    double weight,
    boolean fragile,
    int amount,
    int minimalStock,
    int orderAmount,
    ProductType type,
    @NonNull
    BigDecimal price,
    PartnerDTO supplier
)implements Request<ProductDTO>, Event { }
