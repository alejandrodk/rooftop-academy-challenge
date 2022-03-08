package com.challenge.challenge.models;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class Text {
    private final String id;
    private final String hash;
    private final int chars;
    private final Map<String, Integer> result;
}
