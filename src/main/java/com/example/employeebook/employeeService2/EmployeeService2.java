package com.example.employeebook.employeeService2;


import com.example.employeebook.model.Employee;
import com.example.employeebook.validatorService.ValidatorService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService2 {
    private static final int SIZE = 5;
    private Employee[] staff = new Employee[SIZE];

    private static ValidatorService validatorService;

    public EmployeeService2(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    EmployeeService2() {
        staff[0] = new Employee("Василий", "Васильев", 50000, 1);
        staff[1] = new Employee("Виктор", "Генкин", 51000, 2);
        staff[2] = new Employee("Валерий", "Буров", 53000, 2);
        staff[3] = new Employee("Геннадий", "Букин", 54000, 3);
        staff[4] = new Employee("Георгий", "Гренкин", 55000, 4);
    }

    public Employee getMaxPaidByDept (int department) {
        return Arrays.stream(staff)
                .filter(e -> e != null)
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new IllegalArgumentException("Department number not found"));
    }

    public Employee getMinPaidByDept (int department) {
        return Arrays.stream(staff)
                .filter(e -> e != null)
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new IllegalArgumentException("Department number not found"));
    }

    public List<Employee> showAll () {
        return Arrays.stream(staff)
                .filter(e -> e != null)
                .sorted(Comparator.comparingInt(Employee::getDepartment))
                .collect(Collectors.toList());
    }

    public List<Employee> showByDept (int id) {
        return Arrays.stream(staff)
                .filter(e -> e.getDepartment() == id)
                .collect(Collectors.toList());
    }


}
