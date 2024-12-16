package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.validatorr.EmployeeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.BindException;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public ResponseEntity<?> validateBody(@RequestBody Employee employee) throws BindException {
        EmployeeValidator employeeValidator = new EmployeeValidator();

        WebDataBinder binder = new WebDataBinder(employee);
        binder.setValidator(employeeValidator);

        binder.validate();
        if (binder.getBindingResult().hasErrors()) {
            throw new BindException(binder.getBindingResult().toString());
        }


        employeeRepository.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
