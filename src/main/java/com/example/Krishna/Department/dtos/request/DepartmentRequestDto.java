package com.example.Krishna.Department.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequestDto {


    @NotBlank(message = "Title of the department cannot be blank")
    private String title;

    @JsonProperty("isActive")
    private boolean isActive;
}
