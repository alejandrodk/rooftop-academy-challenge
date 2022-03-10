package com.challenge.challenge.utils;

import com.challenge.challenge.dtos.CreateTextDTO;
import com.challenge.challenge.models.Text;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

public class TextUtilsTest {
    @Test
    public void shouldNormalizeText() {
        String text = "UPPERCASE TEXT";
        String expected = "uppercase text";

        Assertions.assertEquals(expected, TextUtils.normalize(text));
    }

    @Test
    public void shouldValidateIfCharsExceedsText() {
        String success_text = "test";
        int success_chars = 5;

        Assertions.assertTrue(TextUtils.charsExceedsText(success_text, success_chars));

        String error_text = "test";
        int error_chars = 3;

        Assertions.assertFalse(TextUtils.charsExceedsText(error_text, error_chars));
    }

    @Test
    public void shouldReturnTheCorrectCharsQuantity() {
        int ABOVE_CHARS_QUANTITY = 10;
        CreateTextDTO aboveMinChars = new CreateTextDTO("", ABOVE_CHARS_QUANTITY);

        Assertions.assertEquals(ABOVE_CHARS_QUANTITY, TextUtils.getChars(aboveMinChars));

        int UNDER_CHARS_QUANTITY = 1;
        CreateTextDTO underMinChars = new CreateTextDTO("", UNDER_CHARS_QUANTITY);

        Assertions.assertEquals(TextUtils.MIN_CHART_QUANTITY, TextUtils.getChars(underMinChars));
    }

    @Test
    public void shouldThrowErrorOnNullProperties() {
        try {
            Text text = new Text(UUID.randomUUID().toString(), 2, null);

            TextUtils.validateProperties(text);
        } catch (Exception e) {
            Assertions.assertNotNull(e);
        }
    }

    @Test
    public void shouldFlattenResultKeyset() {
        Map<String, Integer> keyset = ImmutableMap.of("foo", 1, "bar", 1);
        String expected = "foo-1;bar-1;";

        Assertions.assertEquals(expected, TextUtils.flattenResult(keyset));
    }

    @Test
    public void shouldUnflattenResultKeyset() {
        String keyset = "foo-1;bar-1;";
        Map<String, Integer> expected = ImmutableMap.of("foo", 1, "bar", 1);

        Assertions.assertEquals(expected, TextUtils.unflattenResult(keyset));
    }

    @Test
    public void shouldMergeLettersFromTheCorrectIndex() {
        String text = "this is a test";
        String expected = "this";
        int from = 0;
        int to = 3;

        Assertions.assertEquals(expected, TextUtils.mergeLettersFromIndex(text, from, to));
    }
}
