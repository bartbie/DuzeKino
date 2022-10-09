package org.duze.duzekino.service;

import org.duze.duzekino.model.Employee;

public class EmployeeNotFoundException extends Throwable {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
