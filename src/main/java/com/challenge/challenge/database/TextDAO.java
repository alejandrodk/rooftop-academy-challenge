package com.challenge.challenge.database;

import com.challenge.challenge.models.IText;
import com.challenge.challenge.models.Text;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@NoArgsConstructor
public class TextDAO implements IText {

    @Autowired
    private JdbcTemplate template;

    @Override
    public List<Text> list() {
        String query = "SELECT * FROM TEXT WHERE active=true";
        return template.query(query, new BeanPropertyRowMapper<Text>(Text.class));
    }

    @Override
    public Text get(int id) {
        String query = String.format("SELECT * FROM TEXT WHERE id=%s;", id);
        List<Text> result = template.query(query, new BeanPropertyRowMapper<Text>(Text.class));

        return result.size() > 0 ? result.get(0) : null;
    }

    @Override
    public Text getByHash(String hash) {
        String query = String.format("SELECT * FROM TEXT WHERE hash='%s';", hash);
        List<Text> result = template.query(query, new BeanPropertyRowMapper<Text>(Text.class));

        return result.size() > 0 ? result.get(0) : null;
    }

    @Override
    public Text create(Text text) {
        String query = String.format(
                "INSERT INTO TEXT VALUES (null,'%s',%s,'%s',%s);",
                text.getHash(),
                text.getChars(),
                text.getResult(),
                text.isActive()
        );

        template.execute(query);

        String queryGet = String.format("SELECT * FROM TEXT WHERE hash='%s';", text.getHash());
        List<Text> result = template.query(queryGet, new BeanPropertyRowMapper<Text>(Text.class));

        return result.get(0);
    }

    @Override
    public boolean delete(int id) {
        String query = String.format(
                "UPDATE TEXT SET (active) = (%s) WHERE id=%s;",
                false,
                id
        );

        template.execute(query);

        String queryGet = String.format("SELECT * FROM TEXT WHERE id=%s AND active=true;", id);
        List<Text> result = template.query(queryGet, new BeanPropertyRowMapper<Text>(Text.class));

        return result.size() == 0;
    }
}
