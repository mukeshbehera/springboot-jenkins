package com.springtest.service;

import com.springtest.entity.Employee;
import com.springtest.error.EmployeeNotFoundException;
import com.springtest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> saveAllEmployee(List<Employee> employeeList) {
        return employeeRepository.saveAll(employeeList);
    }

    public List<Employee> fetchEmployees() {
        return employeeRepository.findAll();
    }

    public Employee fetchEmployeeById(Long employeeId) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (!employee.isPresent()){
            throw new EmployeeNotFoundException("Employee Not Found");
        }
        return employee.get();
    }

    public String deleteEmployeeById(Long employeeId) {
        try{
            employeeRepository.deleteById(employeeId);
        } catch (Exception e){
            throw new RuntimeException("Issue deleting employee with id: " + employeeId);
        }
        return "Employee with id: " + employeeId + " is successfully deleted";
    }

    public Employee updateEmployee(Employee employee, Long employeeId) {
        Optional<Employee> old = employeeRepository.findById(employeeId);
        if (old.isPresent()){
            Employee oldEmployee = old.get();
            if (employee.getEmployeeName() != null && !employee.getEmployeeName().isEmpty()){
                oldEmployee.setEmployeeName(employee.getEmployeeName());
            }
            if (employee.getEmployeeSalary() > 0){
                oldEmployee.setEmployeeSalary(employee.getEmployeeSalary());
            }
            return employeeRepository.save(oldEmployee);
        } else {
            return employeeRepository.save(employee);
        }
    }

    public Employee fetchEmployeeByName(String employeeName) {
        return employeeRepository.findByEmployeeNameIgnoreCase(employeeName);
    }
}
