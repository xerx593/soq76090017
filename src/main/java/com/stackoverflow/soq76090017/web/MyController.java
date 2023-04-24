package com.stackoverflow.soq76090017.web;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.stackoverflow.soq76090017.ResourceNotFoundException;
import com.stackoverflow.soq76090017.service.MyService;

@RestController
class MyController {
    @Autowired
    private MyService userDetailsService;

    @GetMapping("/my/{id}")
    public ResponseEntity<String> myGet(@PathVariable UUID id) throws ResourceNotFoundException {
        return new ResponseEntity<>(userDetailsService.doSomething(id), HttpStatus.OK);
    }
}
