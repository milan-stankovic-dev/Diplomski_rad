package rs.ac.bg.fon.silab.diplomskirad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.domain.BillOfLading;
import rs.ac.bg.fon.silab.diplomskirad.dto.BillOfLadingDTO;
import rs.ac.bg.fon.silab.diplomskirad.mapper.BillOfLadingMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.BillOfLadingRepository;

@RequiredArgsConstructor
@Service
public class BillOfLadingService {
    private final BillOfLadingRepository repository;
    private final BillOfLadingMapper billOfLadingMapper;

    public BillOfLadingDTO insertBillOfLading(BillOfLadingDTO billDTO) {
        var bill = billOfLadingMapper.dTOtoEntity(billDTO);
        var savedBill = repository.save(bill);

        return billOfLadingMapper.entityToDTO(savedBill);
    }
}
