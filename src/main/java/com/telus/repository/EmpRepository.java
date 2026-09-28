package com.telus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.telus.model.Employee;

@Repository
public class EmpRepository {

    private final JdbcTemplate jdbcTemplate;

    public EmpRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Employee InsertEmpRecord(Employee employee) {

        String sql = "INSERT INTO employees (name, Email, Project, Designation) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                employee.getEmp_name(),
                employee.getEmail(),
                employee.getProject(),
                employee.getDesignation());

        Long id = jdbcTemplate.queryForObject(
                "SELECT LAST_INSERT_ID()",
                Long.class);

        employee.setId(id);

        return employee;
    }

    // READ ALL
    public List<Employee> getAllEmpRecords() {

        String sql = "SELECT id, name, email, project, designation FROM employees ORDER BY id";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Employee(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("project"),
                        rs.getString("designation")));
    }

    // READ BY ID
    public Optional<Employee> getEmpById(Long id) {

        String sql = "SELECT id, name, email, project, designation FROM employees  WHERE id = ?";

        List<Employee> employees = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Employee(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("project"),
                        rs.getString("designation")),
                id);

        return employees.stream().findFirst();
    }

    public int updateEmpRecord(Long id, Employee employee) {

        String sql = "UPDATE employees SET name = ?, email = ?, project = ?,  designation = ?  WHERE id = ? ";

        return jdbcTemplate.update(
                sql,
                employee.getEmp_name(),
                employee.getEmail(),
                employee.getProject(),
                employee.getDesignation(),
                id);
    }

    // DELETE
    public int deleteEmpRecord(Long id) {

        String sql = "DELETE FROM employees WHERE id = ?";

        return jdbcTemplate.update(sql, id);
    }

}
