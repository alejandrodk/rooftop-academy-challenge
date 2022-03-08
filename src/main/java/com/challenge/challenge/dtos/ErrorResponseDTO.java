package com.challenge.challenge.dtos;

import lombok.Getter;

@Getter
public class ErrorResponseDTO extends ResponseDTO {
    private final boolean error;
    private final String message;
    private final int code;

    public ErrorResponseDTO() {
        String DEFAULT_MESSAGE = "An error occurred when processing the text.";
        int DEFAULT_STATUS = 422;

        this.error = true;
        this.message = DEFAULT_MESSAGE;
        this.code = DEFAULT_STATUS;
    }

    public ErrorResponseDTO(String message, int code) {
        this.error = true;
        this.message = message;
        this.code = code;
    }
}
