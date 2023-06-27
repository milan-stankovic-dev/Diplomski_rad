package rs.ac.bg.fon.silab.diplomskirad.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "You must input a product name.")
    @NaturalId
    private String productName;
    private double weight;
    private boolean fragile;

    @Min(value = 0, message = "You may not have less than 0 products.")
    private int amount;

    @Enumerated(value = EnumType.STRING)
    private ProductType type;

    @Size(min = 1, max = 1_000_000, message = "Your product must have a reasonable price.")
    private BigDecimal price;

}
