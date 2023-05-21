package com.example.employeebook.departments;


import com.example.employeebook.employeeService2.EmployeeService2;
import com.example.employeebook.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentsController {
    private final EmployeeService2 employeeService2;

    DepartmentsController (EmployeeService2 servis) {
        this.employeeService2 = servis;
    }


@GetMapping("/departments")
public String welcome () {
        return "Welcome to departments";
}
    @GetMapping("/departments/max-salary")
    public Employee getMaxPaidByDept (@RequestParam ("departmentId") Integer id) {
        return employeeService2.getMaxPaidByDept(id);
    }

    @GetMapping("/departments/min-salary")
    public Employee getMinPaidByDept (@RequestParam ("departmentId") Integer id) {
        return employeeService2.getMinPaidByDept(id);
    }

    @GetMapping("/departments/all")
    public List<Employee> getEmployeeByDept (@RequestParam (value = "departmentId", required = false) Integer id) {
        if (id == null) {
            return employeeService2.showAll();
        }
        return employeeService2.showByDept(id);
    }

}
