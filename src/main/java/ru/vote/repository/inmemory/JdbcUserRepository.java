package ru.vote.repository.inmemory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.vote.model.User;
import ru.vote.repository.UserRepository;

import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository {
    //logging in the controller

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert jdbcInsert;

    private final NamedParameterJdbcTemplate jdbcNamed;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate jdbcNamed) {
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcNamed = jdbcNamed;
    }

    @Override
    public User save(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("login", user.getLogin())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail())
                .addValue("registered", user.getRegistered())
                .addValue("enabled", user.isEnabled())
                .addValue("restaurant_id", user.getChoose());

        if (user.isNew()) {
            Number id = jdbcInsert.executeAndReturnKey(map);
            user.setId(id.intValue());
        } else if (jdbcNamed.update(
                "UPDATE users SET login=:login, password=:password, email=:email, " +
                        "registered=:registered, enabled=:enabled, restaurant=:restaurant_id WHERE id=:id", map) == 0) {
            return null;
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User get(int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public User getByEmail(String email) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM users ORDER BY login, email", ROW_MAPPER);
    }
}