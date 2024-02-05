package rs.ac.bg.fon.silab.diplomskirad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @PastOrPresent(message = "Your report may not be set in the future.")
    private Date reportDate;

//    @Size(min = 0, max = 100, message = "Your capacity percentage must be set from 0 to 1000")
    @Min(value = 0, message = "Total capacity must be at least 0")
    @Max(value = 100, message = "Total capacity cannot exceed 100")
    private double totalCapacity;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<ReportItem> reportItems;
}