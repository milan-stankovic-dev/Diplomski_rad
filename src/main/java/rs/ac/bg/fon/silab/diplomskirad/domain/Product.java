package rs.ac.bg.fon.silab.diplomskirad.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    private String productName;
    private double weight;
    private boolean fragile;

    @Min(value = 0, message = "You may not have less than 0 products.")
    private int amount;

    @Enumerated(value = EnumType.STRING)
    private ProductType type;

    @DecimalMin(value = "0", inclusive = false)
    @DecimalMax(value = "1000000")
    private BigDecimal price;

    public void decreaseStockBy(int amountSold){
        this.amount -= amountSold;
    }

    public void increaseStockBy(int amountGained){
        this.amount += amountGained;
    }
}
