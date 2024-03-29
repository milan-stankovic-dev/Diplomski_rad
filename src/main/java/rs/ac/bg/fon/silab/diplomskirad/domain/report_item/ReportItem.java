package rs.ac.bg.fon.silab.diplomskirad.domain.report_item;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.fon.silab.diplomskirad.domain.product.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_report_item")
public class ReportItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Min(value = 0, message = "Product capacity must be at least 0")
    @Max(value = 100, message = "Product capacity cannot exceed 100")
    private double productCapacity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Min(value = 0, message = "No negative capacities allowed.")
    private int totalAvailableCapacity = 200;
}
