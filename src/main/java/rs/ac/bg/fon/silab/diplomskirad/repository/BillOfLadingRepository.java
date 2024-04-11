package rs.ac.bg.fon.silab.diplomskirad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.silab.diplomskirad.domain.bill_of_lading.BillOfLading;

@Repository
public interface BillOfLadingRepository extends JpaRepository<BillOfLading,Long> {

}
