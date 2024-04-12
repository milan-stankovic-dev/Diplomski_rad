package rs.ac.bg.fon.silab.diplomskirad.domain.order_queue;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.fon.silab.diplomskirad.domain.partner.Partner;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_order_queue")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "xml_data", nullable = false,
        columnDefinition = "XML")
    @NotEmpty
    private String xmlData;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private boolean processed;

    @ManyToOne(fetch = FetchType.EAGER,
        cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id")
    private Partner supplier;

}