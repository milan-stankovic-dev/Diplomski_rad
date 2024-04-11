package rs.ac.bg.fon.silab.diplomskirad.domain.xml_schema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_xml_schema")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XmlSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "xml_schema", nullable = false,
        columnDefinition = "XML")
    private String xmlSchema;
}