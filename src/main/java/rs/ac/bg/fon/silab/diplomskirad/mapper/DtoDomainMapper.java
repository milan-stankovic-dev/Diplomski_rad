package rs.ac.bg.fon.silab.diplomskirad.mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public sealed interface DtoDomainMapper<DTO, ENTITY> permits
                                                    ProductMapper,
                                                    FirmMapper,
                                                    PartnerMapper,
                                                    LegalPersonMapper,
                                                    NaturalPersonMapper,
                                                    BillOfLadingMapper,
                                                    RegisterRequestUserMapper,
                                                    AuthRequestUserMapper{
    DTO entityToDTO(ENTITY entity);
    ENTITY dTOtoEntity(DTO dto);

    //treba nam i mapper za kolekciju entiteta i
    //za kolekciju DTO-va.

    default Collection<DTO> collectionOfEntitiesToCollectionOfDTOs(Collection<ENTITY> entityCollection,
                                                                   Class<? extends Collection<DTO>> collectionType)
            throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {

        Constructor<? extends Collection<DTO>> constructor = collectionType.getDeclaredConstructor();
        Collection<DTO> dtoCollection = constructor.newInstance();

        return entityCollection.stream().
                map(this::entityToDTO).
                collect(Collectors.toCollection(() -> dtoCollection));
    }


    default Collection<ENTITY> collectionOfDTOsToCollectionOfEntities(Collection<DTO> dtoCollection,
                                                                      Class<? extends Collection<ENTITY>> collectionType)
            throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {

        Constructor<? extends Collection<ENTITY>> constructor = collectionType.getDeclaredConstructor();
        Collection<ENTITY> entityCollection = constructor.newInstance();

        return dtoCollection.stream().
                map(this::dTOtoEntity).
                collect(Collectors.toCollection(() -> entityCollection));
    }

    //optimizovana verzija. Kada znamo da je kolekcija tipa List, ne moramo da
    //koristimo refleksiju. Bolje performanse.
    default List<ENTITY> listOfDTOsToListOfEntities(List<DTO> dtoList){
        return dtoList.stream().map(this::dTOtoEntity).toList();
    }

    default List<DTO> listOfEntitiesToListOfDTOs(List<ENTITY> entityList){
        return entityList.stream().map(this::entityToDTO).toList();
    }
}
