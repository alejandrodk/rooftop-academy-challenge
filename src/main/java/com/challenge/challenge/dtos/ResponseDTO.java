package com.challenge.challenge.dtos;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public abstract class ResponseDTO {
    @SneakyThrows
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(this);
    }
}
