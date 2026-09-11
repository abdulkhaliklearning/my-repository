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

        String sql = "INSERT INTO employee (Emp_Name, Email, Project, Designation) VALUES (?, ?, ?, ?)";

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

        String sql = "SELECT Emp_Id, Emp_Name, email, project, designation FROM employee ORDER BY Emp_Id";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Employee(
                        rs.getLong("Emp_Id"),
                        rs.getString("Emp_Name"),
                        rs.getString("email"),
                        rs.getString("project"),
                        rs.getString("designation")));
    }

    // READ BY ID
    public Optional<Employee> getEmpById(Long id) {

        String sql = "SELECT Emp_Id, Emp_Name, email, project, designation FROM employee  WHERE Emp_Id = ?";

        List<Employee> employees = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Employee(
                        rs.getLong("Emp_Id"),
                        rs.getString("Emp_Name"),
                        rs.getString("email"),
                        rs.getString("project"),
                        rs.getString("designation")),
                id);

        return employees.stream().findFirst();
    }

    public int updateEmpRecord(Long id, Employee employee) {

        String sql = "UPDATE employee SET Emp_Name = ?, email = ?, project = ?,  designation = ?  WHERE Emp_Id = ? ";

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

        String sql = "DELETE FROM employee WHERE Emp_Id = ?";

        return jdbcTemplate.update(sql, id);
    }

}
