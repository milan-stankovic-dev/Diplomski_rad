package rs.ac.bg.fon.silab.diplomskirad.dto;

public record ReportItemDTO(
    long id,

    double productCapacity,
    ProductDTO product,
    int totalAvailableCapacity
)
{ }
