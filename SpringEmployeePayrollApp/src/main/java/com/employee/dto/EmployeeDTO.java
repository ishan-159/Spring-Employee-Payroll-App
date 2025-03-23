package com.employee.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;

@Data
@Getter
@Setter
@Slf4j
public class EmployeeDTO {

    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z ]{2,}$", message = "Name must start with a capital letter and have at least 3 characters")
    private String name;
    private double salary;

}