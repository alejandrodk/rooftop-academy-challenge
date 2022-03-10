package com.challenge.challenge.services;

import com.challenge.challenge.database.TextDAO;
import com.challenge.challenge.dtos.CreateTextDTO;
import com.challenge.challenge.models.Text;
import com.challenge.challenge.utils.TextUtils;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TextService {
    @Autowired
    TextDAO repository;

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

    private Map<String, Integer> reduceResult(List<String> syllables) {
        HashMap<String, Integer> records = new HashMap<>();
        ImmutableMap.Builder builder = ImmutableMap.builder();

        syllables.forEach(key -> {
            records.put(key, records.containsKey(key) ? records.get(key) + 1 : 1);
            builder.put(key, records.get(key));
        });
        return builder.build();
    }

    public String getHash(CreateTextDTO dto) {
        return UUID.randomUUID().toString();
    }

    private Text analyze(CreateTextDTO dto) {
        String normalizedText = TextUtils.normalize(dto.getText());
        int totalChars = TextUtils.getChars(dto);

        String hash = this.getHash(dto);
        List<String> syllables = this.splitIntoSyllables(normalizedText, totalChars);
        Map<String, Integer> result = this.reduceResult(syllables);
        String normalizedResult = TextUtils.encodeResult(result);

        return new Text(hash, totalChars, normalizedResult);
    }

    public Text create(CreateTextDTO dto) {
        Text text = this.analyze(dto);
        TextUtils.validateProperties(text);

        return repository.create(text);
    }

    public Text get(int id) {
        return repository.get(id);
    }

    public List<Text> list(Integer chars, Integer page, Integer rpp) {
        return repository.list();
    }

    public boolean delete(int id) {
        return repository.delete(id);
    }

    public Text getFromHash(String hash) {
        return repository.getByHash(hash);
    }
}
