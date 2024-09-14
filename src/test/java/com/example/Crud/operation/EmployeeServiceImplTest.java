package com.example.Crud.operation;

import com.example.Crud.operation.data.Employee;
import com.example.Crud.operation.entity.EmployeeEntity;
import com.example.Crud.operation.repository.EmployeeRepository;
import com.example.Crud.operation.services.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void testAddEmployee() {
        // Arrange
        Employee employee = new Employee();
        employee.setName("Faiyaz meghreji");
        employee.setAge("30");
        employee.setSalary("50000");

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("Faiyaz meghreji");
        employeeEntity.setAge("30");
        employeeEntity.setSalary("50000");

        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(employeeEntity);

        // Act
        Employee result = employeeService.addEmployee(employee);

        // Assert
        assertEquals(employee.getName(), result.getName());
        assertEquals(employee.getAge(), result.getAge());
        assertEquals(employee.getSalary(), result.getSalary());
        verify(employeeRepository, times(1)).save(any(EmployeeEntity.class));
    }

    @Test
    public void testUpdateEmployee() {
        Employee employee = new Employee();
        employee.setName("Faiyaz meghreji");
        employee.setAge("30");
        employee.setSalary("50000");

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1L);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employeeEntity));
        when(employeeRepository.save(employeeEntity)).thenReturn(employeeEntity);

        Employee result = employeeService.updateEmployee(employee, 1L);
        assertEquals(employee.getName(), result.getName());
        assertEquals(employee.getAge(), result.getAge());
        assertEquals(employee.getSalary(), result.getSalary());
    }

    @Test
    public void testDeleteEmployee() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1L);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employeeEntity));

        employeeService.deleteEmployee(1L);
        verify(employeeRepository, times(1)).delete(employeeEntity);
    }

    @Test
    public void testGetAllEmployees() {
        List<EmployeeEntity> employeeEntityList = new ArrayList<>();
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("Faiyaz meghreji");
        employeeEntity.setAge("30");
        employeeEntity.setSalary("50000");
        employeeEntityList.add(employeeEntity);

        when(employeeRepository.findAll()).thenReturn(employeeEntityList);

        List<Employee> result = employeeService.getAllEmployees();
        assertEquals(1, result.size());
        assertEquals(employeeEntity.getName(), result.get(0).getName());
        assertEquals(employeeEntity.getAge(), result.get(0).getAge());
        assertEquals(employeeEntity.getSalary(), result.get(0).getSalary());
    }

    @Test
    public void testGetEmployeeById() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1L);
        employeeEntity.setName("Faiyaz meghreji");
        employeeEntity.setAge("30");
        employeeEntity.setSalary("50000");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employeeEntity));

        Employee result = employeeService.getEmployeeById(1L);
        assertEquals(employeeEntity.getName(), result.getName());
        assertEquals(employeeEntity.getAge(), result.getAge());
        assertEquals(employeeEntity.getSalary(), result.getSalary());
    }
}