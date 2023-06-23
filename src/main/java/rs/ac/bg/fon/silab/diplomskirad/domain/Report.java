package rs.ac.bg.fon.silab.diplomskirad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date reportDate;
    private double totalCapacity;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "report")
    @JsonIgnore
    private List<ReportItem> reportItems;
}
