package com.challenge.challenge.services;

import com.challenge.challenge.dtos.CreateTextDTO;
import com.challenge.challenge.models.Text;
import com.challenge.challenge.utils.TextUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TextService {
    private List<String> splitIntoSyllables(String text, int charts) {
        if (TextUtils.charsExceedsText(text, charts)) return Collections.singletonList(text);

        String[] syllables = text.split("");
        List<String> result = new ArrayList<>();

        int lastIndex = 0;
        boolean completed = false;

        while (!completed) {
            result.add(TextUtils.mergeLettersFromIndex(text, lastIndex, lastIndex + charts - 1));

            lastIndex += 1;

            if (lastIndex + charts - 1 == syllables.length) completed = true;
        }

        return result;
    }

    private HashMap<String, Integer> reduceResult(List<String> syllables) {
        return syllables.stream().reduce(
          new HashMap<String, Integer>(),
                (acc, key) -> {
                    acc.put(key, acc.containsKey(key) ? acc.get(key) + 1 : 1);
                    return acc;
                },
                (acc1, acc2) -> {
                    return acc1;
                }
        );
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
        Text text = this.analyze(dto);
        TextUtils.validateProperties(text);

        // TODO: persistir
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
