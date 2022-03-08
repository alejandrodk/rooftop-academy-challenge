package com.challenge.challenge.controllers;

import com.challenge.challenge.dtos.CreateResponseDTO;
import com.challenge.challenge.dtos.CreateTextDTO;
import com.challenge.challenge.dtos.ErrorResponseDTO;
import com.challenge.challenge.services.TextService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class Controller {

    @Autowired
    TextService service;

    @PostMapping("text")
    public ResponseEntity<Object> create(@RequestBody CreateTextDTO payload) {
        try {
            return ResponseEntity.ok(new CreateResponseDTO(UUID.randomUUID().toString(), "test"));
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponseDTO(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("text")
    public ResponseEntity<Object> list() {
        try {
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponseDTO(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("text/{id}")
    public ResponseEntity<Object> detail(@PathVariable String id) {
        try {
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponseDTO(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("text/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        try {
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponseDTO(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
