package com.challenge.challenge.dtos;

import com.challenge.challenge.models.Text;
import com.challenge.challenge.utils.TextUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class TextDTO {
    @JsonProperty
    private int id;
    @JsonProperty
    private final String hash;
    @JsonProperty
    private final int chars;
    @JsonProperty
    private final Map<String, Integer> result;

    public TextDTO(Text text) {
        this.id = text.getId();
        this.hash = text.getHash();
        this.chars = text.getChars();
        this.result = TextUtils.unflattenResult(text.getResult());
    }
}
