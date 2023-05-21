package com.example.employeebook.departments;
import com.example.employeebook.employeeService2.DepartmentService;
import com.example.employeebook.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class DepartmentsController {
    private final DepartmentService departmentService;

    DepartmentsController(DepartmentService servis) {
        this.departmentService = servis;
    }


    @GetMapping("/departments")
    public String welcome() {
        return "Welcome to departments";
    }

    @GetMapping("/departments/max-salary")
    public Employee employeeWithMaxSalary(@RequestParam("departmentId") Integer id) {
        return departmentService.employeeWithMaxSalary(id);
    }

    @GetMapping("/departments/min-salary")
    public Employee employeeWithMinSalary(@RequestParam("departmentId") Integer id) {
        return departmentService.employeeWithMinSalary(id);
    }

    @GetMapping("/departments/all")
    public List<Employee> employeesFromDepartment(@RequestParam(value = "departmentId", required = false) Integer id) {
        if (id == null) {
        }
    }
}