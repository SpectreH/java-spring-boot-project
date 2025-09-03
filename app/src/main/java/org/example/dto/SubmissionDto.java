package org.example.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotEmpty(message = "Select at least one sector")
    private List<Long> sectorValues;

    @AssertTrue(message = "You must agree to terms")
    private boolean agree;
}
