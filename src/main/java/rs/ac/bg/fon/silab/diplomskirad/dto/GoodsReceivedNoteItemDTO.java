package rs.ac.bg.fon.silab.diplomskirad.dto;

public record GoodsReceivedNoteItemDTO(
        long id,
        int amountOrdered,
        ProductDTO product
) { }
