package com.springtest.repository;

import com.springtest.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Employee employee = Employee.builder()
                                    .employeeName("Mukesh")
                                    .employeeSalary(200000)
                                    .build();
        entityManager.persist(employee);
    }

    @Test
    public void whenFindById_thenReturnEmployee(){
        Employee employee = employeeRepository.findById(1L)
                                              .get();
        assertEquals(employee.getEmployeeName(), "Mukesh");
    }
}