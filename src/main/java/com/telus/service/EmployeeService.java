package com.telus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.telus.model.Employee;
import com.telus.repository.EmpRepository;

@Service
public class EmployeeService {

    private final EmpRepository repository;

    public EmployeeService(EmpRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllProducts() {
        return repository.findAll();
    }

    public Employee getEmpById(Long id) {

        Employee emp = repository.findById(id);

        if (emp == null) {
            throw new RuntimeException("Employee not found with id: " + id);
        }

        return emp;
    }

    public Employee createEmployee(Employee emp) {

        return repository.save(emp);
    }

    public Employee updateProduct(Long id, Employee emp) {

        Employee existingEmp = repository.findById(id);

        if (existingEmp == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }

        existingEmp.setName(emp.getName());
        existingEmp.setEmail(emp.getEmail());

        return repository.save(existingEmp);
    }

    public void deleteProduct(Long id) {

        Employee existingEmp = repository.findById(id);

        if (existingEmp == null) {
            throw new RuntimeException("Employee not found with id: " + id);
        }

        repository.deleteById(id);
    }

}
