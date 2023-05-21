package com.example.employeebook.departments;
import com.example.employeebook.employeeService2.DepartmentService;
import com.example.employeebook.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class DepartmentsController {
    private final DepartmentService departmentService;

    public DepartmentsController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping(value = "/{id}/employees")
    public List<Employee> employeesFromDepartment(@PathVariable int id) {
        return departmentService.employeesGroupFromDepartment(id);
    }

    @GetMapping("/{id}/salary/sum")
    public double sumSalaryFromDepartment(@PathVariable int id) {
        return departmentService.sumSalaryFromDepartment(id);
    }

    @GetMapping("/{id}/salary/max")
    public double maxSalaryFromDepartment(@PathVariable int id) {
        return departmentService.maxSalaryFromDepartment(id);
    }

    @GetMapping("/{id}/salary/min")
    public double minSalaryFromDepartment(@PathVariable int id) {
        return departmentService.minSalaryFromDepartment(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> employeesGroupByDepartment() {
        return departmentService.employeesGroupByDepartment();
    }
}