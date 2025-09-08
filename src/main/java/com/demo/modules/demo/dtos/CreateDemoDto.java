package com.demo.modules.demo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDemoDto {
    @NotBlank(message = "Name")
    @Schema(description = "Name of the broker agent", example = "C.H Robinson")
    private String name;

    @Schema(description = "Demo Age", type = "int", example = "25")
    private int age;
}
