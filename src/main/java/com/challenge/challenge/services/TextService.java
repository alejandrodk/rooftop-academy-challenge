package com.challenge.challenge.services;

import com.challenge.challenge.database.TextRepository;
import com.challenge.challenge.dtos.CreateTextDTO;
import com.challenge.challenge.models.Text;
import com.challenge.challenge.utils.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

@Service
public class TextService {
    @Autowired
    TextRepository repository;

    public String getHash(CreateTextDTO dto) {
        try {
            String raw = String.format("%s-%s", dto.getText(), dto.getChars());

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(raw.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();

            BigInteger bigInt = new BigInteger(1, digest);
            String hash = bigInt.toString(16);

            return hash;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Text analyze(CreateTextDTO dto) {
        String normalizedText = TextUtils.normalize(dto.getText());
        int totalChars = TextUtils.getChars(dto);

        String hash = this.getHash(dto);
        List<String> syllables = TextUtils.splitIntoSyllables(normalizedText, totalChars);
        Map<String, Integer> result = TextUtils.convertToMap(syllables);
        String normalizedResult = TextUtils.flattenResult(result);

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

    public List<Text> list(Optional<Integer> chars, Optional<Integer> page, Optional<Integer> rpp) {
        return repository.list(chars, page, rpp);
    }

    public boolean delete(int id) {
        return repository.delete(id);
    }

    public Text getFromHash(String hash) {
        return repository.getByHash(hash);
    }
}
