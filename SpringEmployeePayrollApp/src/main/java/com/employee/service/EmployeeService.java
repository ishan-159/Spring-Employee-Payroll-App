package com.employee.service;


import com.employee.exceptionhandling.EmployeeNotFoundException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private final List<Employee> employeeList = new ArrayList<>();

    public Employee createEmployee(Employee employee) {
        log.info("Creating a new employee: {}", employee);
        long newId = employeeList.isEmpty() ? 1 : employeeList.get(employeeList.size() - 1).getId() + 1;
        employee.setId(newId);
        employeeList.add(employee);
        return employee;
    }

    public List<Employee> getAllEmployees()
    {
        log.info("Fetching all employees from database");
        return employeeList;
    }

    public Optional<Employee> getEmployeeById(Long id) {
        log.info("Fetching employee with ID {}", id);
        return Optional.ofNullable(employeeList.stream().filter(employee -> employee.getId().equals(id))
                .findFirst().orElseThrow(() -> new EmployeeNotFoundException(("Employee with ID " + id + " not found!"))));
    }
    //update
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        log.info("Updating employee with ID {}", id);
        Optional<Employee> existingEmployee = getEmployeeById(id);
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            employee.setName(updatedEmployee.getName());
            employee.setSalary(updatedEmployee.getSalary());
            return employee;
        } else {
            log.error("Employee with ID {} not found for update", id);
            throw new RuntimeException("Employee not found");
        }
    }

    public void deleteEmployee(Long id) {
        log.info("Deleting employee with ID {}" , id);
        employeeList.removeIf(employee -> employee.getId().equals(id));
        log.info("Employee deleted successfully");
    }
}