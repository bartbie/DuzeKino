package org.duze.duzekino.service;

import lombok.RequiredArgsConstructor;
import org.duze.duzekino.model.Employee;
import org.duze.duzekino.model.employee;
import org.duze.duzekino.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    final EmployeeRepository employeeRepository;

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public Employee getEmployeeByEmployeeName(String employeeName) throws EmployeeNotFoundException{
        Optional<Employee> result = employeeRepository.findEmployeeByFullName(employeeName);
        if (result.isPresent()) {
            return result.get();
        } throw new EmployeeNotFoundException("employee not found with employeeName: " + employeeName);
    }

    public Employee getEmployee(Long id) throws EmployeeNotFoundException {
        Optional<Employee> result = employeeRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } throw new EmployeeNotFoundException("employee not found with id: " + id);
    }

    public void deleteEmployee(Long id) throws EmployeeNotFoundException {
        Optional<Employee> result = employeeRepository.findById(id);
        if(result.isPresent()) {
            employeeRepository.deleteById(id);
        }
        throw new EmployeeNotFoundException("employee not found with id: " + id);
    }
}
