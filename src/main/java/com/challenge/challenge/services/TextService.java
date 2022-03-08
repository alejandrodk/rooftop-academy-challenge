package com.challenge.challenge.services;

import com.challenge.challenge.dtos.CreateTextDTO;
import com.challenge.challenge.models.Text;
import com.challenge.challenge.utils.TextUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class TextService {
    private List<String> splitIntoSyllables(String text, int charts) {
        if (TextUtils.charsExceedsText(text, charts)) return Collections.singletonList(text);
        // TODO:

        return Collections.emptyList();
    }

    private HashMap<String, Integer> reduceResult(List<String> syllables) {
        // TODO:
        return new HashMap<>();
    }

    private Text analyze(CreateTextDTO dto) {
        String normalizedText = TextUtils.normalize(dto.getText());
        int totalChars = TextUtils.getChars(dto);

        String hash = UUID.randomUUID().toString();
        List<String> syllables = this.splitIntoSyllables(normalizedText, totalChars);
        HashMap<String, Integer> result = this.reduceResult(syllables);

        return new Text(hash, totalChars, result);
    }

    public Text create(CreateTextDTO dto) {
        // TODO:
        Text text = this.analyze(dto);
        TextUtils.validateProperties(text);
        text.setId(1);

        return text;
    }

    public Text get(String id) {
        // TODO:
        return new Text("", 1, new HashMap<>());
    }

    public List<Text> list(Integer chars, Integer page, Integer rpp) {
        // TODO:
        return Collections.emptyList();
    }

    public boolean delete(String id) {
        // TODO:
        return true;
    }
}
