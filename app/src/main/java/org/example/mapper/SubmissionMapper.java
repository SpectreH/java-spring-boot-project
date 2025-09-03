package org.example.mapper;

import org.example.dto.SubmissionDto;
import org.example.entity.Sector;
import org.example.entity.Submission;
import org.mapstruct.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface SubmissionMapper {
    @Mapping(target = "sectorValues",
            expression = "java(toSectorValues(s.getSectors()))")
    SubmissionDto toDto(Submission s);

    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(target = "name", source = "dto.name"),
            @Mapping(target = "agree", source = "dto.agree"),
            @Mapping(target = "sectors", source = "pickedSectors")
    })
    void applyDto(@MappingTarget Submission target,
                  SubmissionDto dto,
                  Set<Sector> pickedSectors);

    default List<Long> toSectorValues(Set<Sector> sectors) {
        if (sectors == null) return List.of();
        return sectors.stream()
                .sorted(Comparator.comparing(Sector::getId))
                .map(Sector::getSectorValue)
                .toList();
    }
}
