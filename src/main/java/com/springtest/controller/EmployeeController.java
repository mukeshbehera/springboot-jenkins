package com.springtest.controller;

import com.springtest.entity.Employee;
import com.springtest.error.EmployeeNotFoundException;
import com.springtest.service.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping(path = "/employees")
    public Employee saveEmployee(@Valid @RequestBody Employee employee){
        LOGGER.info("Inside saveEmployee method");
        return employeeService.saveEmployee(employee);
    }

    @PostMapping(path = "/employees/all")
    public List<Employee> saveAllEmployee(@RequestBody List<Employee> employeeList){
        return employeeService.saveAllEmployee(employeeList);
    }

    @GetMapping(path = "/employees/all")
    public List<Employee> fetchEmployees(){
        return employeeService.fetchEmployees();
    }

    @GetMapping(path = "/employees/{id}")
    public Employee fetchEmployeeById(@PathVariable("id") Long employeeId) throws EmployeeNotFoundException {
        return employeeService.fetchEmployeeById(employeeId);
    }

    @DeleteMapping(path = "/employees/{id}")
    public String deleteEmployeeById(@PathVariable("id") Long employeeId){
        return employeeService.deleteEmployeeById(employeeId);
    }

    @PutMapping(path = "/employees/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("id") Long employeeId){
        return employeeService.updateEmployee(employee, employeeId);
    }

    @GetMapping(path = "employees/name/{name}")
    public Employee fetchEmployeeByName(@PathVariable("name") String employeeName){
        return employeeService.fetchEmployeeByName(employeeName);
    }
}
