package com.challenge.challenge.integration;

import com.challenge.challenge.SeniorityBoostChallengeApplication;
import com.challenge.challenge.dtos.*;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SeniorityBoostChallengeApplication.class)
public class TextIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private Integer port;

    @Test
    public void createNewText() {
        CreateTextDTO dto = new CreateTextDTO("test", 2);
        ResponseEntity<CreateResponseDTO> responseEntity = this.restTemplate
                .postForEntity(String.format("http://localhost:%s/text", port), dto, CreateResponseDTO.class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals("/text/1", responseEntity.getBody().getUrl());
    }

    @Test
    public void softDeleteText() {
        CreateTextDTO dto = new CreateTextDTO("test", 2);
        // Create Text
        ResponseEntity<CreateResponseDTO> creationResponse = this.restTemplate
                .postForEntity(String.format("http://localhost:%s/text", port), dto, CreateResponseDTO.class);

        Assertions.assertEquals(HttpStatus.OK, creationResponse.getStatusCode());

        // Validate if Text exists
        ResponseEntity<TextDTO> detailResponse = this.restTemplate
                .getForEntity(String.format("http://localhost:%s/text/%s", port, 1), TextDTO.class);

        Assertions.assertEquals(HttpStatus.OK, detailResponse.getStatusCode());

        // Delete Text
        this.restTemplate.delete(String.format("http://localhost:%s/text/%s", port, 1));

        // Validate if Text exists
        ResponseEntity<TextDTO[]> detailResponseAfterDelete = this.restTemplate
                .getForEntity(String.format("http://localhost:%s/text", port), TextDTO[].class);

        Assertions.assertEquals(HttpStatus.OK, detailResponseAfterDelete.getStatusCode());
        Assertions.assertNotNull(detailResponseAfterDelete.getBody());
        Assertions.assertEquals(0, detailResponseAfterDelete.getBody().length);

    }

    @Test
    public void returnErrorOnInvalidId() {
        ResponseEntity<ErrorResponseDTO> detailResponse = this.restTemplate
                .getForEntity(String.format("http://localhost:%s/text/%s", port, 99), ErrorResponseDTO.class);

        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, detailResponse.getStatusCode());
        Assertions.assertTrue(detailResponse.getBody().isError());
    }

    @Test
    public void testChallengeExample() {
        CreateTextDTO dto = new CreateTextDTO("solo se que nada se", 6);
        ImmutableMap<String, Integer> expectedResult = new ImmutableMap.Builder<String, Integer>()
                .put("solo s", 1).put("olo se", 1).put("lo se ", 1).put("o se q", 1).put(" se qu", 1)
                .put("se que", 1).put("e que ", 1).put(" que n", 1).put("que na", 1).put("ue nad", 1)
                .put("e nada", 1).put(" nada ", 1).put("nada s", 1).put("ada se", 1).build();

        ResponseEntity<CreateResponseDTO> response = this.restTemplate
                .postForEntity(String.format("http://localhost:%s/text", port), dto, CreateResponseDTO.class);

        Assertions.assertNotNull(response.getBody());

        ResponseEntity<TextDTO> responseEntity = this.restTemplate
                .getForEntity(String.format("http://localhost:%s/text/%s", port, response.getBody().getId()), TextDTO.class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(expectedResult, responseEntity.getBody().getResult());
    }
}
