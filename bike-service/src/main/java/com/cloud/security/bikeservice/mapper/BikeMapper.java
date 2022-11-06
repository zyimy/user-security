package com.cloud.security.bikeservice.mapper;

import com.cloud.security.bikeservice.dto.BikeDto;
import com.cloud.security.bikeservice.entity.Bike;
import java.util.List;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL, injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    componentModel = "spring")
public interface BikeMapper {

  BikeDto toDto(Bike entity);

  Bike toEntity(BikeDto dto);

  List<Bike>toListEntity(List<BikeDto>listDto);

  List<BikeDto>toListDto(List<Bike>listEntity);
}
