package com.demo.modules.demo.mapper;

import org.mapstruct.*;

import com.demo.modules.demo.dtos.CreateDemoDto;
import com.demo.modules.demo.dtos.DemoDto;
import com.demo.modules.demo.dtos.UpdateDemoDto;
import com.demo.modules.demo.entities.DemoEntity;

@Mapper(componentModel = "spring")
public interface DemoMapper {
    DemoDto toDto(DemoEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    DemoEntity toEntity(CreateDemoDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(UpdateDemoDto dto, @MappingTarget DemoEntity entity);

}