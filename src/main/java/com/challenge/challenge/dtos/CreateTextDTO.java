package com.challenge.challenge.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateTextDTO {
    private final String text;
    private final int chars;
}
