package com.example.employeebook;

import com.example.employeebook.employeeService2.DepartmentService;
import com.example.employeebook.exception.DepartmentNotFoundException;
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


    public static Stream<Arguments> maxSalaryFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, 15000 ),
                Arguments.of(2, 17000 ),
                Arguments.of(3, 20000 )
        );
    }

    public static Stream<Arguments> minSalaryFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, 10000 ),
                Arguments.of(2, 15000 ),
                Arguments.of(3, 20000 )
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
    @MethodSource("maxSalaryFromDepartmentTestParams")
    public void maxSalaryFromDepartmentTest(int departmentId, double expected) {
        Assertions.assertThat(departmentService.maxSalaryFromDepartment(departmentId))
                .isEqualTo(expected);

    }

    @Test
    public void maxSalaryWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(()->departmentService.maxSalaryFromDepartment(4));
    }

    @ParameterizedTest
    @MethodSource("minSalaryFromDepartmentTestParams")
    public void minSalaryFromDepartmentTest(int departmentId, double expected) {
        Assertions.assertThat(departmentService.minSalaryFromDepartment(departmentId))
                .isEqualTo(expected);

    }

    @Test
    public void minSalaryWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(()->departmentService.minSalaryFromDepartment(5));
    }

    @ParameterizedTest
    @MethodSource("employeesFromDepartmentTestParams")
    public void employeesFromDepartmentTest(int departmentId, List<Employee> expected) {
        Assertions.assertThat(departmentService.employeesGroupFromDepartment(departmentId))
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
        ));
        Assertions.assertThat(departmentService.employeesGroupByDepartment())
                .containsExactlyInAnyOrderEntriesOf(expected);

    }
}
