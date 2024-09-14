package com.example.Crud.operation.services;

import com.example.Crud.operation.data.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    Employee addEmployee(Employee employee);

    // Update employee
    Employee updateEmployee(Employee employee,Long id);

    // Delete employee
    void deleteEmployee(Long id);

    // Get all employees
    List<Employee> getAllEmployees();

    // Get employee by id
    Employee getEmployeeById(Long id);
}
