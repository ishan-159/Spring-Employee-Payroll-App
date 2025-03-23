package com.employee.controller;

import com.employee.dto.EmployeeDTO;
import com.employee.model.Employee;
import com.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    //UC4
    @Autowired
    private EmployeeService employeeService;

    //UC1  // UC2  //UC3
    // GET all employees
    @GetMapping("/all")
    public List<Employee> getEmployees() {
        log.info(" GET Request: Fetching all employees");
        return employeeService.getAllEmployees();
    }

    @GetMapping("/get/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        log.info("GET Request: Fetching employee with ID {}", id);
        return employeeService.getEmployeeById(id);
    }

    //    Add new employee
    @PostMapping("/create")
    public Employee createEmployee( @RequestBody Employee employee) {
        log.info("POST Request: Creating employee with data {}", employee);
        return employeeService.createEmployee(employee);
    }

    @PostMapping("/createDto")
    public Employee CreateEmployeeDto(@Valid @RequestBody EmployeeDTO employeeDTO) {
        log.info("POST Request: Creating employee DTO with data {}", employeeDTO);
        Employee employee = new Employee(employeeDTO.getName(), employeeDTO.getSalary());
        return employeeService.createEmployee(employee);
    }

    // PUT - Update an existing employee
    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        log.info("PUT Request: Updating employee with ID {}", id);
        return employeeService.updateEmployee(id, updatedEmployee);
    }

    // DELETE - Remove an employee
    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        log.info("DELETE Request: Deleting employee with ID {}", id);
        employeeService.deleteEmployee(id);
        return "Employee "+id +" deleted successfully!";
    }


}