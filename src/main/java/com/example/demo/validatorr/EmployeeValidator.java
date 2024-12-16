package com.example.demo.validatorr;

import com.example.demo.entity.Employee;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EmployeeValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee = (Employee) target;

        if (employee.getFullName() == null || employee.getFullName().isEmpty()) {
            errors.rejectValue("fullName", "fullname.manadatory", "The full name is mandatory field");
        }

        if (employee.getMobileNumber() == null || employee.getMobileNumber().isEmpty()) {
            errors.rejectValue("mobileNumber", "mobilenumber.manadatory", "The mobile number is mandatory field");
        }

        if (employee.getEmailId() == null || employee.getEmailId().isEmpty()) {
            errors.rejectValue("emailId", "emailid.manadatory", "The email id is mandatory field");
        }else if(!employee.getEmailId().contains("@")){
            errors.rejectValue("emailId", "emailId.invalidFormat", "The emailId should be in valid email format");
        }

        if(employee.getDateOfBirth() == null || employee.getDateOfBirth().isEmpty()){
            errors.rejectValue("dateOfBirth", "dateOfBirth.mandatory", "The date of birth is mandatory field");
        }else if(!employee.getDateOfBirth().matches("\\d{4}-\\d{2}-\\d{2}")){
            errors.rejectValue("dateOfBirth", "dateOfBirth.invalidFormat", "The date of birth should be in YYYY-MM-DD format");
        }
    }

    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }
}
