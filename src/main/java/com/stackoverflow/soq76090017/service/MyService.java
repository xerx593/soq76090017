package com.stackoverflow.soq76090017.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class MyService {
    @SuppressWarnings("java:S106")
    public String doSomething(UUID id) {
        System.err.println(id.toString() + " done.");
        return id.toString();
    }
}
