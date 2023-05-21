package com.example.employeebook;

import com.example.employeebook.employeeService2.DepartmentService;
import com.example.employeebook.exception.EmployeeNotFoundException;
import com.example.employeebook.model.Employee;
import com.example.employeebook.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;


    public static Stream<Arguments> employeeWithMaxSalaryTestParams() {
        return Stream.of(
                Arguments.of(1,new Employee("Пётр", "Петров", 15000, 1 )),
                Arguments.of(2,new Employee("Анна", "Петрова", 17000, 2 )),
                Arguments.of(3,new Employee("Вася", "Пупкин", 20000, 3 ))
        );
    }

    public static Stream<Arguments> employeeWithMinSalaryTestParams() {
        return Stream.of(
                Arguments.of(1,new Employee("Иван", "Иванов", 10000, 1 )),
                Arguments.of(2,new Employee("Мария", "Иванова", 15000, 2 )),
                Arguments.of(3,new Employee("Вася", "Пупкин", 20000, 3 ))
        );
    }

    public static Stream<Arguments> employeesFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, List.of(
                        new Employee("Иван", "Иванов", 10000, 1),
                        new Employee("Пётр", "Петров", 15000, 1)
                        )
                ),
                Arguments.of(2, List.of(
                        new Employee("Мария", "Иванова", 15000, 2),
                        new Employee("Анна", "Петрова", 17000, 2)
                        )
                ),
                        Arguments.of(3, Collections.singletonList(new Employee("Вася", "Пупкин", 20000, 3))
                        ),
                Arguments.of(4, Collections.emptyList())
        ); }

    @BeforeEach
    public void beforeEach(){
        Mockito.when(employeeService.getAll()).thenReturn(
                List.of(
          new Employee("Иван", "Иванов", 10000, 1),
          new Employee("Мария", "Иванова", 15000, 2),
          new Employee("Пётр", "Петров", 15000, 1),
          new Employee("Анна", "Петрова", 17000, 2),
          new Employee("Вася", "Пупкин", 20000, 3)
        ));
    }

    @ParameterizedTest
    @MethodSource("employeeWithMaxSalaryTestParams")
    public void employeeWithMaxSalaryTest(int departmentId, Employee expected) {
        Assertions.assertThat(departmentService.employeeWithMaxSalary(departmentId))
                .isEqualTo(expected);

    }

    @Test
    public void employeeWithMaxSalaryWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->departmentService.employeeWithMaxSalary(4));
    }

    @ParameterizedTest
    @MethodSource("employeeWithMinSalaryTestParams")
    public void employeeWithMinSalaryTest(int departmentId, Employee expected) {
        Assertions.assertThat(departmentService.employeeWithMinSalary(departmentId))
                .isEqualTo(expected);

    }

    @Test
    public void employeeWithMinSalaryWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->departmentService.employeeWithMinSalary(4));
    }

    @ParameterizedTest
    @MethodSource("employeesFromDepartmentTestParams")
    public void employeesFromDepartmentTest(int departmentId, List<Employee> expected) {
        Assertions.assertThat(departmentService.employeesFromDepartment(departmentId))
                .containsExactlyInAnyOrderElementsOf(expected);

    }

    @Test
    public void employeesGroupedByDepartmentTest() {
        Map<Integer, List<Employee>> expected = Map.of(
                1, List.of(new Employee("Иван", "Иванов", 10000, 1),
                        new Employee("Пётр", "Петров", 15000, 1)),
                2, List.of(new Employee("Мария", "Иванова", 15000, 2),
                        new Employee("Анна", "Петрова", 17000, 2)),
                3, Collections.singletonList((new Employee("Вася", "Пупкин", 20000, 3))
        );
        Assertions.assertThat(departmentService.employeesGroupByDepartment())
                .containsExactlyInAnyOrderEntriesOf(expected);

    }
}
