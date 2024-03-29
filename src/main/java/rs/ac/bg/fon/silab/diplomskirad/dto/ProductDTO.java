package rs.ac.bg.fon.silab.diplomskirad.dto;

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
    ProductType type,
    @NonNull
    BigDecimal price
) { }
