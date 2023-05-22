package com.example.employeebook.model;
import java.util.Objects;

public class Employee {
    private String  Name;
    private String surName;

    private double salary;

    private int department;

    public Employee(String name, String surName, double salary, int department) {
        Name = name;
        this.surName = surName;
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

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
        return Double.compare(employee.getSalary(), getSalary()) == 0 && getDepartment() == employee.getDepartment() && Objects.equals(getName(), employee.getName()) && Objects.equals(getSurName(), employee.getSurName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurName(), getSalary(), getDepartment());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Name='" + Name + '\'' +
                ", surName='" + surName + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }

    public Employee() {
        super();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
