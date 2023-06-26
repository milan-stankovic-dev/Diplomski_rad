package rs.ac.bg.fon.silab.diplomskirad.mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public sealed interface DtoDomainMapper<DTO, ENTITY> permits
                                                    ProductMapper,
                                                    FirmMapper,
                                                    PartnerMapper,
                                                    LegalPersonMapper,
                                                    NaturalPersonMapper,
                                                    BillOfLadingMapper,
                                                    RegisterRequestUserMapper,
                                                    AuthRequestUserMapper,
                                                    BillOfLadingItemMapper,
                                                    GoodsReceivedNoteMapper,
                                                    GoodsReceivedNoteItemMapper,
                                                    ReportItemMapper,
                                                    ReportMapper,
                                                    BuyerMapper{
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
