package org.duze.duzekino.service;

import org.duze.duzekino.model.User;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String message) {
        super(message);
    }
}
