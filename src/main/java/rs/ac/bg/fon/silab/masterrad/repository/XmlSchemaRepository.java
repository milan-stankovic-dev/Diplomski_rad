package rs.ac.bg.fon.silab.masterrad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.silab.masterrad.domain.xml_schema.XmlSchema;

public interface XmlSchemaRepository extends JpaRepository<XmlSchema, Long> {
}
