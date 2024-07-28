package com.springbootweb.springbootweb.Annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation, String> {
    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext) {
        if(inputRole==null){
            return false;
        }
        List<String> list = List.of("Admin","User");
        return list.contains(inputRole);
    }
}
