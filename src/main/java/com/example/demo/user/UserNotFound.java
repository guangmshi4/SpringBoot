package com.example.demo.user;

public class UserNotFound extends Throwable {
    public UserNotFound(String message) {
        super(message);
    }
}
