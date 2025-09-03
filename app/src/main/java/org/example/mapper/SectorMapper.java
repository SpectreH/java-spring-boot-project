package org.example.mapper;

import org.example.dto.SectorDto;
import org.example.dto.SectorSelectDto;
import org.example.entity.Sector;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SectorMapper {
    @Mapping(target = "sectorValue", source = "sectorValue")
    @Mapping(target = "label", expression = "java(indent(s.getLevel()) + s.getName())")
    SectorSelectDto toSelectDto(Sector s);

    default String indent(Integer level) {
        int lvl = (level == null ? 0 : level);
        return "\u00A0".repeat(lvl * 4);
    }
}