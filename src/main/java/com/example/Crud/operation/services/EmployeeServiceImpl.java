package com.example.Crud.operation.services;

import com.example.Crud.operation.data.Employee;
import com.example.Crud.operation.entity.EmployeeEntity;
import com.example.Crud.operation.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    ArrayList<Employee> list = new ArrayList<>();
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        list.add(employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        if (employeeRepository.findById(id).isPresent()) {
            EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
            employeeEntity.setName(employee.getName());
            employeeEntity.setAge(employee.getAge());
            employeeEntity.setSalary(employee.getSalary());
            employeeRepository.save(employeeEntity);
        }
        return employee;
    }

    @Override
    public void deleteEmployee(Long id) {
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.delete(employeeRepository.findById(id).get());
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        return employeeEntityList.stream()
                .map(employeeEntity -> {
                    Employee employee = new Employee();
                    BeanUtils.copyProperties(employeeEntity, employee);
                    return employee;
                })
                .toList();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Employee employee = new Employee();
        if (employeeRepository.findById(id).isPresent()) {
            BeanUtils.copyProperties(employeeRepository.findById(id).get(), employee);
        }
        return employee;
    }
}
