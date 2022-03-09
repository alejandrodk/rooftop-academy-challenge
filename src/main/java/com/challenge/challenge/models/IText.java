package com.challenge.challenge.models;

import java.util.List;

public interface IText {
    public List<Text> list();
    public Text get(int id);
    public Text getByHash(String hash);
    public Text create(Text text);
    public boolean delete(int id);
}
