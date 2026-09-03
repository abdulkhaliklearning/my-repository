package com.telus.controller;

import com.telus.model.Employee;
import com.telus.service.EmployeeService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class MainController {

    private final EmployeeService service;

    public MainController(EmployeeService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Employee> createEmployee(
            @RequestBody Employee emp) {

        Employee createEmployee = service.createEmployee(emp);

        return new ResponseEntity<>(
                createEmployee,
                HttpStatus.CREATED);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Employee>> getAllProducts() {

        return ResponseEntity.ok(
                service.getAllProducts());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmpById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getEmpById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateProduct(
            @PathVariable Long id,
            @RequestBody Employee product) {

        return ResponseEntity.ok(
                service.updateProduct(id, product));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id) {

        service.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}
