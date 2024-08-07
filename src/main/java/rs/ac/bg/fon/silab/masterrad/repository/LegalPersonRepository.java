package rs.ac.bg.fon.silab.masterrad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.silab.masterrad.domain.legal_person.LegalPerson;

@Repository
public interface LegalPersonRepository extends JpaRepository<LegalPerson, Long> {
}
