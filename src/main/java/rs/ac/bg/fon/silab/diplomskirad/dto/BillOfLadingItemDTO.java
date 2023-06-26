package rs.ac.bg.fon.silab.diplomskirad.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record BillOfLadingItemDTO(
        long id,
        int amountSold,
        ProductDTO product
)
{ }
