package rs.ac.bg.fon.silab.masterrad.domain.product;

import com.fasterxml.jackson.databind.BeanProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import rs.ac.bg.fon.silab.masterrad.domain.enumeration.ProductType;
import rs.ac.bg.fon.silab.masterrad.domain.partner.Partner;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(exclude = {"price", "currentStock",
        "minimalStock", "orderAmount", "supplier"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "You must input a product name.")
    private String productName;

    private double weight;

    private boolean fragile;

    @Min(value = 0, message = "You may not have less than 0 products.")
    private int currentStock;

    @Min(value = 0, message = "Your minimal stock must be a positive integer value.")
    private int minimalStock;

    @Min(value = 0, message = "You must order at least one product") //VRATI NA 1!!
    private int orderAmount;

    @Enumerated(value = EnumType.STRING)
    private ProductType type;

    @DecimalMin(value = "0", inclusive = false)
    @DecimalMax(value = "1000000")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    private Partner supplier;

    @NaturalId
    @Column(unique = true, updatable = false, nullable = false)
    private UUID code = UUID.randomUUID();

    public void decreaseStockBy(int amountSold){
        this.currentStock -= amountSold;
    }

    public void increaseStockBy(int amountGained){
        this.currentStock += amountGained;
    }

}
