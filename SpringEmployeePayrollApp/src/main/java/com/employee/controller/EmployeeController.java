package com.employee.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    //UC1
    @GetMapping
    public String getEmployees() {
        return "This is GET Mapping";
    }

    @PostMapping
    public String addEmployee() {
        return "This is POST Mapping";
    }

    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable Long id) {
        return "This is PUT Mapping for employee ID: " + id;
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        return "This is DELETE Mapping for employee ID: " + id;
    }
}
