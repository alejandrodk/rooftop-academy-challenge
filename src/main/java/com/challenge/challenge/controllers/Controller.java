package com.challenge.challenge.controllers;

import com.challenge.challenge.dtos.CreateResponseDTO;
import com.challenge.challenge.dtos.CreateTextDTO;
import com.challenge.challenge.dtos.ErrorResponseDTO;
import com.challenge.challenge.dtos.TextDTO;
import com.challenge.challenge.models.Text;
import com.challenge.challenge.services.TextService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {

    @Autowired
    TextService service;

    @PostMapping("text")
    public ResponseEntity<Object> create(@RequestBody CreateTextDTO body) {
        try {
            Text result = service.create(body);

            return ResponseEntity.ok(new CreateResponseDTO(result.getId(), String.format("/text/%s", result.getId())));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponseDTO(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("text")
    public ResponseEntity<Object> list(@RequestParam int chars, @RequestParam int page, @RequestParam int rpp) {
        try {
            List<Text> result = service.list(chars, page, rpp);
            List<TextDTO> dtos = result.stream().map(TextDTO::new).collect(Collectors.toList());

            return ResponseEntity.ok(dtos);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponseDTO(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("text/{id}")
    public ResponseEntity<Object> detail(@PathVariable String id) {
        try {
            Text result = service.get(id);
            TextDTO dto = new TextDTO(result);

            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponseDTO(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("text/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        try {
            boolean result = service.delete(id);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponseDTO(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
