package org.duze.duzekino.repository;

import lombok.RequiredArgsConstructor;
import org.duze.duzekino.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeeByFullName(String fullName);
}
