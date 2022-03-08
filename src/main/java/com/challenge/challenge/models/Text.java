package com.challenge.challenge.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Text {
    @JsonProperty
    private int id;
    @JsonProperty
    private final String hash;
    @JsonProperty
    private final int chars;
    @JsonProperty
    private final Map<String, Integer> result;
    @JsonProperty
    private boolean active = true;

    public Text(String hash, int chars, Map<String, Integer> result) {
        this.hash = hash;
        this.chars = chars;
        this.result = result;
    }
}
