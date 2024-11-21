package ru.practicum.dto.mainservice.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestCategoryDto {

    @NotBlank(message = "Name of category cant be blank or null")
    @Size(min = 1, max = 50)
    private String name;
}