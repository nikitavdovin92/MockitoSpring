package com.example.employeebook.service;

import com.example.employeebook.exception.EmployeeAlreadyAddedException;
import com.example.employeebook.exception.EmployeeNotFoundException;
import com.example.employeebook.model.Employee;
import com.example.employeebook.validatorService.ValidatorService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public abstract class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;

    public EmployeeServiceImpl () { this.employees = new HashMap<>(); }

    @Override
    public Employee add(String firstName, String lastName, double salary, int department) {
        Employee employee = new Employee(ValidatorService.validateFirstName(lastName),
                ValidatorService.validateLastName(firstName), salary, department);
        if(employees.containsKey(employee.fullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.fullName(), employee);
        return employee;
    }
    @Override
    public Employee remove(String firstName, String lastName, double salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if(employees.containsKey(employee.fullName())) {
            return employees.remove(employee.fullName());
        }
        throw new EmployeeNotFoundException();
    }
    @Override
    public Employee find (String firstName, String lastName, double salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if(employees.containsKey(employee.fullName())) {
            return employees.remove(employee.fullName());
        }
        throw new EmployeeNotFoundException();
    }


    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
