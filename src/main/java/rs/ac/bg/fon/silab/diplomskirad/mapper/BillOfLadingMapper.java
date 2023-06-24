package rs.ac.bg.fon.silab.diplomskirad.mapper;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.BillOfLading;
import rs.ac.bg.fon.silab.diplomskirad.dto.BillOfLadingDTO;

@Component
public non-sealed class BillOfLadingMapper implements DtoDomainMapper<BillOfLadingDTO, BillOfLading> {

    @Override
    public BillOfLadingDTO entityToDTO(BillOfLading billOfLading) {
        return null;
    }

    @Override
    public BillOfLading dTOtoEntity(BillOfLadingDTO billOfLadingDTO) {
        return null;
    }
}
