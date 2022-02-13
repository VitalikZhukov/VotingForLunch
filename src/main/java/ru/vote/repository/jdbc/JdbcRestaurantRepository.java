package ru.vote.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vote.model.Restaurant;
import ru.vote.repository.RestaurantRepository;
import ru.vote.util.ValidationUtil;

import java.util.List;


@Repository
@Transactional(readOnly = true)
public class JdbcRestaurantRepository implements RestaurantRepository {
    //logging in the controller

    private static final BeanPropertyRowMapper<Restaurant> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Restaurant.class);

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert jdbcInsert;

    private final NamedParameterJdbcTemplate jdbcNamed;

    @Autowired
    public JdbcRestaurantRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate jdbcNamed) {
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("restaurants")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcNamed = jdbcNamed;
    }


    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        ValidationUtil.validate(restaurant);

        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", restaurant.getId())
                .addValue("name", restaurant.getName())
                .addValue("voteCounter", restaurant.getVoteCounter());

        if (restaurant.isNew()) {
            Number id = jdbcInsert.executeAndReturnKey(map);
            restaurant.setId(id.intValue());
        } else if (jdbcNamed.update(
                "UPDATE restaurants SET name=:name, vote_counter=:voteCounter WHERE id=:id", map) == 0) {
            return null;
        }
        return restaurant;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM restaurants WHERE id=?", id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        List<Restaurant> restaurants = jdbcTemplate.query("SELECT * FROM restaurants WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(restaurants);
    }

    @Override
    public List<Restaurant> getAll() {
        List<Restaurant> list = jdbcTemplate.query("SELECT * FROM restaurants ORDER BY id", ROW_MAPPER);

        return jdbcTemplate.query("SELECT * FROM restaurants ORDER BY id", ROW_MAPPER);
    }

    @Override
    @Transactional
    public boolean incrementVoteCounter(int id) {
        int voteCounter = get(id).getVoteCounter();
        voteCounter++;
        return jdbcTemplate.update("UPDATE restaurants SET vote_counter=? WHERE id=?", voteCounter, id) != 0;
    }

    @Override
    public int getVoteCounter(int id) {
        return get(id).getVoteCounter();
    }

    @Override
    @Transactional
    public void resetAllRestaurantId() {
        jdbcTemplate.update("UPDATE restaurants SET vote_counter=0");
    }
}
