package rs.ac.bg.fon.silab.masterrad.dto;

public record GoodsReceivedNoteItemDTO(
        long id,
        int amountOrdered,
        ProductDTO product
) { }
