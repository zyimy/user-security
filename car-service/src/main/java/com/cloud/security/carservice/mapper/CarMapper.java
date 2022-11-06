package com.cloud.security.carservice.mapper;

import com.cloud.security.carservice.dto.CarDto;
import com.cloud.security.carservice.entity.Car;
import java.util.List;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL, injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    componentModel = "spring")
public interface CarMapper {

  CarDto toDto(Car entity);

  Car toEntity(CarDto dto);

  List<Car>toListEntity(List<CarDto>listDto);

  List<CarDto>toListDto(List<Car>listEntity);
}
