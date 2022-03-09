package com.challenge.challenge.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Text {
    @JsonProperty
    private int id;
    @JsonProperty
    private String hash;
    @JsonProperty
    private int chars;
    @JsonProperty
    private String result;
    @JsonProperty
    private boolean active = true;

    public Text(String hash, int chars, String result) {
        this.hash = hash;
        this.chars = chars;
        this.result = result;
    }
}
