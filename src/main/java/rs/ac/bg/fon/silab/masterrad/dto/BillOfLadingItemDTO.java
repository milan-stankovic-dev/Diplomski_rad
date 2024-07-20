package rs.ac.bg.fon.silab.masterrad.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record BillOfLadingItemDTO(
        long id,
        int amountSold,
        ProductDTO product
)
{ }
