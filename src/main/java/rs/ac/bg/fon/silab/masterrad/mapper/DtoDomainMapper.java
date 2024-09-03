package rs.ac.bg.fon.silab.masterrad.mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public sealed interface DtoDomainMapper<DTO, ENTITY> permits AuthRequestUserMapper, BillOfLadingItemMapper, BillOfLadingMapper, BuyerMapper, FirmMapper, GoodsReceivedNoteItemMapper, GoodsReceivedNoteMapper, LegalPersonMapper, NaturalPersonMapper, PartnerMapper, ProductFullMapper, ProductMapper, RegisterRequestUserMapper, ReportItemMapper, ReportMapper, TokenValidationRequestMapper {
    DTO entityToDTO(ENTITY entity);
    ENTITY dTOtoEntity(DTO dto);

    default List<ENTITY> listOfDTOsToListOfEntities(List<DTO> dtoList){
        return dtoList.stream().map(this::dTOtoEntity).toList();
    }

    default List<DTO> listOfEntitiesToListOfDTOs(List<ENTITY> entityList){
        return entityList.stream().map(this::entityToDTO).toList();
    }

    default Set<ENTITY> setOfDTOsToSetOfEntities(Set<DTO> dtoSet){
        return dtoSet.stream().map(this::dTOtoEntity).collect(Collectors.toSet());
    }

    default Set<DTO> setOfEntitiesSetOfDTOs(Set<ENTITY> entitySet){
        return entitySet.stream().map(this::entityToDTO).collect(Collectors.toSet());
    }
}
