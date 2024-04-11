package rs.ac.bg.fon.silab.diplomskirad.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.legal_person.LegalPerson;
import rs.ac.bg.fon.silab.diplomskirad.domain.natural_person.NaturalPerson;
import rs.ac.bg.fon.silab.diplomskirad.domain.abstraction.Buyer;
import rs.ac.bg.fon.silab.diplomskirad.dto.BuyerDTO;
import rs.ac.bg.fon.silab.diplomskirad.dto.LegalPersonDTO;
import rs.ac.bg.fon.silab.diplomskirad.dto.NaturalPersonDTO;

@Component
@RequiredArgsConstructor
public non-sealed class BuyerMapper
        implements DtoDomainMapper<BuyerDTO, Buyer> {
    private final NaturalPersonMapper naturalPersonMapper;
    private final LegalPersonMapper legalPersonMapper;

    @Override
    public BuyerDTO entityToDTO(Buyer buyer) {
        if(buyer instanceof NaturalPerson){
            return naturalPersonMapper.entityToDTO((NaturalPerson) buyer);
        }
        else if(buyer instanceof LegalPerson){
            return legalPersonMapper.entityToDTO((LegalPerson)buyer);
        }
        else
            throw new IllegalArgumentException("Wrong type");
    }

    @Override
    public Buyer dTOtoEntity(BuyerDTO buyerDTO) {
        if(buyerDTO instanceof NaturalPersonDTO){
            return naturalPersonMapper.dTOtoEntity((NaturalPersonDTO) buyerDTO);
        }
        else if(buyerDTO instanceof LegalPersonDTO){
            return legalPersonMapper.dTOtoEntity((LegalPersonDTO)buyerDTO);
        }
        else
            throw new IllegalArgumentException("Wrong type");
    }
}
