package rs.ac.bg.fon.silab.masterrad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.silab.masterrad.domain.natural_person.NaturalPerson;

@Repository
public interface NaturalPersonRepository extends JpaRepository<NaturalPerson, Long> {
}
