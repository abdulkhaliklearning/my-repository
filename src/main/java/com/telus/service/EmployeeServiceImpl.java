package com.telus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.telus.model.Employee;
import com.telus.repository.EmpRepository;
import com.telus.exception.EmployeeNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmpRepository employeeRepository;

    public EmployeeServiceImpl(
            EmpRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmpRecords() {
        return employeeRepository.getAllEmpRecords();
    }

    @Override
    public Employee InsertEmpRecord(Employee employee) {
        return employeeRepository.InsertEmpRecord(employee);
    }

    @Override
    public Employee getEmpById(Long id) {

        return employeeRepository.getEmpById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Employee not found with id: " + id));
    }

    @Override
    public Employee updateEmpRecord(
            Long id,
            Employee employee) {

        Employee existingEmployee = employeeRepository.getEmpById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Employee not found with id: " + id));

        employeeRepository.updateEmpRecord(id, employee);

        employee.setId(existingEmployee.getId());

        return employee;
    }

    @Override
    public void deleteEmpRecord(Long id) {

        employeeRepository.getEmpById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Employee not found with id: " + id));

        employeeRepository.deleteEmpRecord(id);
    }

}