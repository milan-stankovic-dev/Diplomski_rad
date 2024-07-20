package rs.ac.bg.fon.silab.masterrad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.silab.masterrad.domain.report_item.ReportItem;

public interface ReportItemRepository
        extends JpaRepository<ReportItem,Long> {

}
