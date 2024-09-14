package com.example.Crud.operation;

import com.example.Crud.operation.controller.EmployeeController;
import com.example.Crud.operation.data.Employee;
import com.example.Crud.operation.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void testAddEmployee() {
        // Arrange
        Employee employee = new Employee();
        employee.setName("Faiyaz Meghreji");
        employee.setAge("32");
        employee.setSalary("10000");

        when(employeeService.addEmployee(employee)).thenReturn(employee);

        // Act
        Employee result = employeeController.addEmployee(employee);

        // Assert
        assertEquals(employee, result);
        verify(employeeService, times(1)).addEmployee(employee);
    }

    @Test
    public void testGetAllEmployees() {
        // Arrange
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.setName("Faiyaz Meghreji");
        employee.setAge("32");
        employee.setSalary("10000");
        employees.add(employee);

        when(employeeService.getAllEmployees()).thenReturn(employees);

        // Act
        List<Employee> result = employeeController.getAllEmployees();

        // Assert
        assertEquals(employees, result);
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    public void testGetEmployeeById() {
        // Arrange
        Employee employee = new Employee();
        employee.setName("Faiyaz Meghreji");
        employee.setAge("32");
        employee.setSalary("10000");

        when(employeeService.getEmployeeById(1L)).thenReturn(employee);

        // Act
        Employee result = employeeController.getEmployeeById(1L);

        // Assert
        assertEquals(employee, result);
        verify(employeeService, times(1)).getEmployeeById(1L);
    }

    @Test
    public void testUpdateEmployee() {
        // Arrange
        Employee employee = new Employee();
        employee.setName("Faiyaz Meghreji");
        employee.setAge("32");
        employee.setSalary("10000");

        when(employeeService.updateEmployee(employee, 1L)).thenReturn(employee);

        // Act
        Employee result = employeeController.updateEmployee(employee, 1L);

        // Assert
        assertEquals(employee, result);
        verify(employeeService, times(1)).updateEmployee(employee, 1L);
    }

    @Test
    public void testDeleteEmployee() {
        // Act
        employeeController.deleteEmployee(1L);

        // Assert
        verify(employeeService, times(1)).deleteEmployee(1L);
    }
}