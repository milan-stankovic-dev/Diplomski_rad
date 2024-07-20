package rs.ac.bg.fon.silab.masterrad.dto;

public record ReportItemDTO(
    long id,

    double productCapacity,
    ProductDTO product,
    int totalAvailableCapacity
)
{ }
