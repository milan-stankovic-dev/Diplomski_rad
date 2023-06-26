package rs.ac.bg.fon.silab.diplomskirad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.silab.diplomskirad.domain.ReportItem;
import rs.ac.bg.fon.silab.diplomskirad.dto.ReportItemDTO;

public interface ReportItemRepository
        extends JpaRepository<ReportItem,Long> {

}
