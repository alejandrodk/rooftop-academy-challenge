package com.challenge.challenge.utils;

import com.challenge.challenge.dtos.CreateTextDTO;
import com.challenge.challenge.models.Text;

import java.lang.reflect.Field;

public class TextUtils {
    private TextUtils() {}

    private static final int MIN_CHART_QUANTITY = 2;

    public static String normalize(String text) {
        return text.toLowerCase();
    }

    public static boolean charsExceedsText(String text, int charts) {
        return charts > text.length();
    }

    public static int getChars(CreateTextDTO dto) {
        return Math.max(dto.getChars(), MIN_CHART_QUANTITY);
    }

    public static void validateProperties(Text text) {
        Field[] fields = text.getClass().getDeclaredFields();

        for (Field field: fields) {
            try {
                field.setAccessible(true);

                String key = field.getName();
                Object value = field.get(text);

                if (value == null) {
                    throw new IllegalAccessException(String.format("Unable to process text, field %s has an invalid value", key));
                }
            } catch (IllegalAccessException ignore) {}
        }
    }

    public static String mergeLettersFromIndex(String text, int from, int to) {
      StringBuilder sb = new StringBuilder();
      String[] split = text.split("");

      for(int i = from; i <= to; i++) {
          sb.append(split[i]);
      }

      return sb.toString();
    };
}
