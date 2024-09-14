package com.example.Crud.operation.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private String age;

    @Column(name = "salary")
    private String salary;

}