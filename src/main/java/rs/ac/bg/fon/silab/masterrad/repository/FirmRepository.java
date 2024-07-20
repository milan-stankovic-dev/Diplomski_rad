package rs.ac.bg.fon.silab.masterrad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.silab.masterrad.domain.firm.Firm;

@Repository
public interface FirmRepository extends JpaRepository<Firm,Long> {
}
