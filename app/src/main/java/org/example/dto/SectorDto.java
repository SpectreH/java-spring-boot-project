package org.example.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectorDto {
    private Long id;
    private Long sectorValue;
    private String name;
    private Integer level;
}
