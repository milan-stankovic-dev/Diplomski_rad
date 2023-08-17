package rs.ac.bg.fon.silab.diplomskirad.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ReportItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
