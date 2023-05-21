package com.example.employeebook.employeeService2;


import com.example.employeebook.exception.EmployeeNotFoundException;
import com.example.employeebook.model.Employee;
import com.example.employeebook.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    public Employee employeeWithMaxSalary(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() = departmentId)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee employeeWithMinSalary(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() = departmentId)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> employeesFromDepartment(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() = departmentId)
                .collect(Collectors.toList());
    }

    public Map<Integer, List <Employee>> employeesGroupByDepartment(int id) {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }


}
