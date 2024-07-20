package rs.ac.bg.fon.silab.masterrad.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.domain.report_item.ReportItem;
import rs.ac.bg.fon.silab.masterrad.dto.ReportItemDTO;

@Component
@RequiredArgsConstructor
public non-sealed class ReportItemMapper
        implements DtoDomainMapper<ReportItemDTO, ReportItem> {
    private final ProductMapper productMapper;

    @Override
    public ReportItemDTO entityToDTO(ReportItem reportItem) {
        var reportItemDTO = new ReportItemDTO(
                reportItem.getId(),
                reportItem.getProductCapacity(),
                productMapper.entityToDTO(reportItem.getProduct()),
                reportItem.getTotalAvailableCapacity()
        );

        return reportItemDTO;
    }

    @Override
    public ReportItem dTOtoEntity(ReportItemDTO reportItemDTO) {

        var reportItem = new ReportItem(
                reportItemDTO.id(),
                reportItemDTO.productCapacity(),
                productMapper.dTOtoEntity(reportItemDTO.product()),
                reportItemDTO.totalAvailableCapacity()
        );

        return reportItem;
    }
}
