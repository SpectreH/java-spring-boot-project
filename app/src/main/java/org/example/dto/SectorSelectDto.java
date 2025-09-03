package org.example.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectorSelectDto {
    private Long sectorValue;
    private String label;
}
