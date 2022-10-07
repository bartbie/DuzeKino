package org.duze.duzekino.service;

import org.assertj.core.api.Assertions;
import org.duze.duzekino.model.Employee;
import org.duze.duzekino.model.Schedule;
import org.duze.duzekino.repository.EmployeeRepository;
import org.duze.duzekino.repository.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepo;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    ScheduleRepository scheduleRepo;

    //set up the default state of database before each test method
    @BeforeEach
    void setUp() {
        Schedule schedule = new Schedule(true, true, true, true, true, true, true);
        scheduleRepo.save(schedule);
        Employee employee = new Employee("FullName", "Position", "Email", schedule);
        employeeRepo.deleteAll();
        employeeRepo.save(employee);
    }

    @Test
    public void testAddEmployee() throws EmployeeNotFoundException {
        Employee employeeId = new Employee();
        employeeId.setFullName("employeeName");
        employeeId.setPosition("position");
        Schedule schedule = new Schedule(true, true, true, true, true, true, true);
        scheduleRepo.save(schedule);
        employeeId.setSchedule(schedule);
        employeeId.setEmail("email");

        employeeService.saveEmployee(employeeId);
        Employee savedEmployee = employeeService.getEmployeeByEmployeeName("employeeName");
        Assertions.assertThat(savedEmployee).isNotNull();
    }

    @Test
    public void testListAll() {
        Iterable<Employee> employees = employeeService.getAllEmployees();
        Assertions.assertThat(employees).hasSizeGreaterThan(0);
        for (Employee employee : employees
        ) {
            System.out.println(employees);
        }
    }

    @Test
    public void testUpdate() throws EmployeeNotFoundException {
        List<Employee> employees = (List<Employee>) employeeService.getAllEmployees();
        Employee lastEmployee = employees.get(employees.size() - 1);
        Long employeeId = lastEmployee.getId();
        Employee employee = employeeService.getEmployee(employeeId);
        String newEmail = "newEmailSet";
        employee.setEmail(newEmail);
        employeeService.saveEmployee(employee);

        Employee updatedEmployee = employeeService.getEmployee(employeeId);
        Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo(newEmail);
    }

    @Test
    public void testGet() throws EmployeeNotFoundException {
        List<Employee> employees = employeeService.getAllEmployees();
        Employee lastEmployee = employees.get(employees.size() - 1);
        Long employeeId = lastEmployee.getId();
        Employee employee = employeeService.getEmployee(employeeId);
        Assertions.assertThat(employee).isNotNull();
        System.out.println(employee);
    }

    @Test
    public void testDelete() throws EmployeeNotFoundException {
        List<Employee> employees = (List<Employee>) employeeService.getAllEmployees();
        Employee lastEmployee = employees.get(employees.size() - 1);
        Long employeeId = lastEmployee.getId();
        employeeService.deleteEmployee(employeeId);
        Optional<Employee> optional = employeeRepo.findById(employeeId);
        Assertions.assertThat(optional).isNotPresent();
    }
}
