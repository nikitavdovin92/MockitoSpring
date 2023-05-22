package com.example.employeebook;


import com.example.employeebook.exception.*;
import com.example.employeebook.model.Employee;
import com.example.employeebook.service.EmployeeService;
import com.example.employeebook.validatorService.ValidatorService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService(new ValidatorService());

    public static Stream<Arguments>addWithIncorrectNameTestParams() {
        return Stream.of(
                Arguments.of("Иван1"),
                Arguments.of("Иван!"),
                Arguments.of("Иван@")
        );
    }

    public static Stream<Arguments>addWithIncorrectSurnameTestParams() {
        return Stream.of(
                Arguments.of("Иванов1"),
                Arguments.of("Иванов!"),
                Arguments.of("Иванов@")
        );
    }

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


    //Тесты для add
    @Test
    public void addTest() {
        int beforeCount = employeeService.getAll().size();
        Employee expected = new Employee("Ivan", "Ivanov", 50000, 1);

        Assertions.assertThat(employeeService.add("Ivan", "Ivanov", 50000, 1))
                .isEqualTo(expected)
                .isIn(employeeService.getAll());
        Assertions.assertThat(employeeService.getAll()).hasSize(beforeCount+1);
        Assertions.assertThat(employeeService.find("Ivan", "Ivanov")).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource ("addWithIncorrectNameTestParams")
    public void addWithIncorrectNameTest(String incorrectName) {
        Assertions.assertThatExceptionOfType(IncorrectFirstnameException.class)
                .isThrownBy(()->employeeService.add(incorrectName, "Ivanov", 50_000, 1));
    }

    @ParameterizedTest
    @MethodSource ("addWithIncorrectSurnameTestParams")
    public void addWithIncorrectSurnameTest(String incorrectSurname) {
        Assertions.assertThatExceptionOfType(IncorrectLastnameException.class)
                .isThrownBy(()->employeeService.add("Ivan",incorrectSurname, 50_000, 1));
    }

    @Test
    public void addWhenAlreadyExistsTest() {
        Assertions.assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(()->employeeService.add("Иван","Иванов", 50_000, 1));
    }

    @Test
    public void addWhenStorageIsFullTest() {
        Stream.iterate(1,i->i+1)
                        .limit(7)
                                .map(number->new Employee("Иван"+ ((char) ('а'+number)),
                                        "Иванов"+((char) ('а'+number)),
                                        10000+number,
                                        number ))
                                        .forEach(employee ->
                                                employeeService.add
                                                        (employee.getName(),
                                                                employee.getSurName(),
                                                                employee.getSalary(), employee.getDepartment())
                                        );

        Assertions.assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(()->employeeService.add("Вася","Пупкин", 50000, 1));
    }


    //Тесты для remove
    @Test
    public void removeTest() {
        int beforeCount = employeeService.getAll().size();
        Employee expected = new Employee("Иван", "Иванов", 50000, 1);

        Assertions.assertThat(employeeService.remove("Иван", "Иванов"))
                .isEqualTo(expected)
                .isNotIn(employeeService.getAll());
        Assertions.assertThat(employeeService.getAll()).hasSize(beforeCount-1);
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->employeeService.find("Иван","Иванов"));
    }

    @Test
    public void removeWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->employeeService.find("Вася","Пупкин"));
    }

    //Тесты для find
    @Test
    public void findTest() {
        int beforeCount = employeeService.getAll().size();
        Employee expected = new Employee("Иван", "Иванов", 50000, 1);

        Assertions.assertThat(employeeService.find("Иван", "Иванов"))
                .isEqualTo(expected)
                .isIn(employeeService.getAll());
        Assertions.assertThat(employeeService.getAll()).hasSize(beforeCount);
    }

    @Test
    public void findWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->employeeService.find("Вася","Пупкин"));
    }

    //Тесты для getAll
    @Test
    public void getAllTest() {
        Assertions.assertThat(employeeService.getAll())
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Employee("Иван","Иванов", 50000, 1),
                     new Employee   ("Пётр","Петров",60000, 2),
                       new Employee ("Сергей","Сергеев",70000, 3)
                );

    }
}
