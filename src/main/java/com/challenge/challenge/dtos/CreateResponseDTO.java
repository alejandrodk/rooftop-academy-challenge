package com.challenge.challenge.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateResponseDTO extends ResponseDTO {
    private final String id;
    private final String url;
}
