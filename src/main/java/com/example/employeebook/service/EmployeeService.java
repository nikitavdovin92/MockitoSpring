package com.example.employeebook.service;

import com.example.employeebook.exception.EmployeeAlreadyAddedException;
import com.example.employeebook.exception.EmployeeNotFoundException;
import com.example.employeebook.exception.EmployeeStorageIsFullException;
import com.example.employeebook.model.Employee;
import com.example.employeebook.validatorService.ValidatorService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private static final int LIMIT = 10;
    private final List<Employee> employees = new ArrayList<>();

    private final ValidatorService validatorService;

    public EmployeeService(ValidatorService validatorService) { this.validatorService = validatorService; }


    public Employee add(String name, String surName, double salary, int department) {
        Employee employee = new Employee(ValidatorService.validateName(name),
                ValidatorService.validateSurname(surName), salary, department);
        if(employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size()< LIMIT) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    public Employee remove(String name, String surName) {
        Employee employee = employees.stream()
                .filter(emp->emp.getName().equals(name) && emp.getSurName().equals(surName))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
        employees.remove(employee);
        return employee;
    }

    public Employee find (String name, String surName) {
        return employees.stream()
                .filter(employee -> employee.getName().equals(name) && employee.getSurName().equals(surName))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }



    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }

}
