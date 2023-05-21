package com.example.employeebook.model;
import java.util.Objects;

public class Employee {
    private String  firstName;
    private String lastName;

    private double salary;

    private int department;

    public Employee(String firstName, String lastName, double salary, int department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() {return lastName; }

    public String fullName() { return firstName + " " + lastName; }

    //Getters and setters


    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Double.compare(employee.getSalary(), getSalary()) == 0 && getDepartment() == employee.getDepartment() && Objects.equals(getFirstName(), employee.getFirstName()) && Objects.equals(getLastName(), employee.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getSalary(), getDepartment());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                ", department=" + department +
                '}';
    }
}
