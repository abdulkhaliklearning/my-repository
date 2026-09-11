package com.telus.service;

import java.util.List;
import com.telus.model.Employee;

public interface EmployeeService {

    Employee InsertEmpRecord(Employee employee);

    List<Employee> getAllEmpRecords();

    Employee getEmpById(Long id);

    Employee updateEmpRecord(Long id, Employee employee);

    void deleteEmpRecord(Long id);
}