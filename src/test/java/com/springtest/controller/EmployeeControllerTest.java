package com.springtest.controller;

import com.springtest.entity.Employee;
import com.springtest.error.EmployeeNotFoundException;
import com.springtest.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;
    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .employeeId(1L)
                .employeeName("Mukesh")
                .employeeSalary(200000)
                .build();
    }

    @Test
    void saveEmployee() throws Exception {
        Employee inputEmployee = Employee.builder()
                                     .employeeName("Mukesh")
                                     .employeeSalary(200000)
                                     .build();
        Mockito.when(employeeService.saveEmployee(inputEmployee))
               .thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content("{\n"
                                                       + "\t\t\"employeeName\": \"Mukesh\",\n"
                                                       + "\t\t\"employeeSalary\": 200000\n"
                                                       + "}"))
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk());
    }

    @Test
    void fetchEmployeeById() throws Exception {
        Mockito.when(employeeService.fetchEmployeeById(1L))
               .thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/1")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.employeeName")
                                               .value(employee.getEmployeeName()));
    }
}