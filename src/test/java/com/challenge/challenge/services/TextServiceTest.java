package com.challenge.challenge.services;

import com.challenge.challenge.SeniorityBoostChallengeApplication;
import com.challenge.challenge.database.TextRepository;
import com.challenge.challenge.dtos.CreateTextDTO;
import com.challenge.challenge.models.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SeniorityBoostChallengeApplication.class)
public class TextServiceTest {
    @Autowired
    TextService service;

    @MockBean
    private TextRepository repository;

    @Test
    public void ShouldGetTheCorrectHashFromDTO() {
        CreateTextDTO dto = new CreateTextDTO("test", 2);
        CreateTextDTO dto2 = new CreateTextDTO("test", 2);

        String hash1 = service.getHash(dto);
        String hash2 = service.getHash(dto2);

        Assertions.assertEquals(hash1, hash2);
        Assertions.assertEquals(32, hash1.length());
    }

    @Test
    public void ShoudlCreateValidTextFromDTO() {
        CreateTextDTO dto = new CreateTextDTO("test", 2);

        Text result = service.analyze(dto);

        Assertions.assertNotNull(result.getHash());
        Assertions.assertNotNull(result.getResult());
        Assertions.assertEquals(2, result.getChars());
    }
}
