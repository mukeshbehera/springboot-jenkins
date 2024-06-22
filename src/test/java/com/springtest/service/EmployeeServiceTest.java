package com.springtest.service;

import com.springtest.entity.Employee;
import com.springtest.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;
    @MockBean
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        Employee employee = Employee.builder()
                                    .employeeId(11)
                                    .employeeName("Mukesh")
                                    .employeeSalary(200000)
                                    .build();
        Mockito.when(employeeRepository.findByEmployeeNameIgnoreCase("Mukesh"))
               .thenReturn(employee);
    }

    @Test
    @DisplayName("Get data based on valid employee name")
    public void whenValidEmployeeName_employeeShouldReturn(){
        String employeeName = "Mukesh";
        Employee employee = employeeService.fetchEmployeeByName(employeeName);
        assertEquals(employee.getEmployeeName(), employeeName);
    }
}