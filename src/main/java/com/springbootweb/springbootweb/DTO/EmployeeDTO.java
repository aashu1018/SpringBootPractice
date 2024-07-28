package com.springbootweb.springbootweb.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotNull(message = "Name of the employee cannot be null")
    @NotBlank(message = "Name of the employee cannot be blank")
    @Size(min = 3, max = 10, message = "Name should have a minimum of 3 and a maximum of 10 characters")
    private String name;

    @Email(message = "Email should be a valid email")
    private String email;

    @Min(value = 18, message = "Minimum age of the employee should be 18")
    @Max(value = 60, message = "Maximum age of the employees should be 60")
    private Integer age;

    @NotBlank(message = "Role of the employee cannot be blank")
    @Pattern(regexp = "^(Admin|User)$", message = "Role of employee can be either Admin or User")
    private String role;

    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    private Boolean isActive;

    @NotNull(message = "Salary of the employee cannot be null")
    @Positive(message = "Salary of the employee should be positive")
    private Double salary;

}
