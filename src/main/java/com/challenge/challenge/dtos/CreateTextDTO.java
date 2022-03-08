package com.challenge.challenge.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateTextDTO {
    private final String text;
    private int chars;
}
