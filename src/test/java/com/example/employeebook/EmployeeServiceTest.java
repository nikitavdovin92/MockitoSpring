package com.example.employeebook;


import com.example.employeebook.model.Employee;
import com.example.employeebook.service.EmployeeService;
import com.example.employeebook.validatorService.ValidatorService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

public class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeService(new ValidatorService());

    @BeforeEach
    public void beforeEach() {
        employeeService.add("Иван","Иванов", 50000, 1);
        employeeService.add("Пётр","Петров",60000, 2);
        employeeService.add("Сергей","Сергеев",70000, 3);
    }
    @AfterEach
    public void afterEach() {
        employeeService.getAll().forEach(employee -> employeeService.remove
                (employee.getName(), employee.getSurName()));
    }
    @Test
    public void addTest() {
        int beforeCount = employeeService.getAll().size();
        Employee expected = new Employee("Ivan", "Ivanov", 50000, 1);

        Assertions.assertThat(employeeService.add("Ivan", "Ivanov", 50000, 1))
                .isEqualTo(expected)
                .isIn(employeeService.getAll());
        Assertions.assertThat(employeeService.getAll()).hasSize(beforeCount+1);

    }
}
