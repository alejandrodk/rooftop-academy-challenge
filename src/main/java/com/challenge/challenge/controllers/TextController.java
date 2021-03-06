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
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TextController {

    @Autowired
    TextService service;

    @PostMapping("text")
    public ResponseEntity<Object> create(@RequestBody CreateTextDTO body) {
        try {
            Text existsInDB = service.getFromHash(service.getHash(body));
            Text result = existsInDB != null ? existsInDB : service.create(body);

            return ResponseEntity.ok(new CreateResponseDTO(result.getId(), String.format("/text/%s", result.getId())));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponseDTO(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("text")
    public ResponseEntity<Object> list(
            @RequestParam Optional<Integer> chars,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> rpp
    ) {
        try {
            List<Text> result = service.list(chars, page, rpp);
            List<TextDTO> dtos = result.stream().map(TextDTO::new).collect(Collectors.toList());

            return ResponseEntity.ok(dtos);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponseDTO(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("text/{id}")
    public ResponseEntity<Object> detail(@PathVariable int id) {
        try {
            Text result = service.get(id);

            if (result == null) {
                return new ResponseEntity<>(new ErrorResponseDTO("Text not found", 404), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            TextDTO dto = new TextDTO(result);

            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponseDTO(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("text/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        try {
            boolean result = service.delete(id);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponseDTO(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
