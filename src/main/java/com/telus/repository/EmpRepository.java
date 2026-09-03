package com.telus.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.telus.model.Employee;

@Repository
public class EmpRepository {

    private final Map<Long, Employee> employee = new HashMap<>();

    public List<Employee> findAll() {
        return new ArrayList<>(employee.values());
    }

    public Employee findById(Long id) {
        return employee.get(id);
    }

    public Employee save(Employee emp) {
        employee.put(emp.getId(), emp);
        return emp;
    }

    public void deleteById(Long id) {
        employee.remove(id);
    }

}
