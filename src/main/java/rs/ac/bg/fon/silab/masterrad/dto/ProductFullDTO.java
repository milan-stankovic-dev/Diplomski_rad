package rs.ac.bg.fon.silab.masterrad.dto;

import io.jkratz.mediator.core.Event;
import io.jkratz.mediator.core.Request;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import rs.ac.bg.fon.silab.masterrad.domain.enumeration.ProductType;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductFullDTO(
        long id,
        @NotNull
        String productName,
        double weight,
        boolean fragile,
        int currentStock,
        int minimalStock,
        int orderAmount,
        ProductType type,
        @NotNull
        BigDecimal price,
        @Valid
        PartnerDTO supplier,
        UUID code
)implements Request<ProductDTO>, Event { }