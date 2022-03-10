package com.challenge.challenge.models;

import java.util.List;
import java.util.Optional;

public interface IText {
    public List<Text> list(Optional<Integer> chars, Optional<Integer> page, Optional<Integer> rpp);
    public Text get(int id);
    public Text getByHash(String hash);
    public Text create(Text text);
    public boolean delete(int id);
}
