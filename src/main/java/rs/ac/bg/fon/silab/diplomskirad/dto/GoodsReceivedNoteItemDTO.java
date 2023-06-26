package rs.ac.bg.fon.silab.diplomskirad.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import rs.ac.bg.fon.silab.diplomskirad.domain.Product;

public record GoodsReceivedNoteItemDTO(
        long id,
        int amountOrdered,
        ProductDTO product
) { }
