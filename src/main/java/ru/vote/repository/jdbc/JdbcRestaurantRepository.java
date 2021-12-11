package ru.vote.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.vote.model.Menu;
import ru.vote.model.Restaurant;
import ru.vote.repository.RestaurantRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Repository
public class JdbcRestaurantRepository implements RestaurantRepository {
    //logging in the controller

    private static final BeanPropertyRowMapper<Restaurant> ROW_MAPPER_REST = BeanPropertyRowMapper.newInstance(Restaurant.class);
    private static final BeanPropertyRowMapper<Menu> ROW_MAPPER_MENU = BeanPropertyRowMapper.newInstance(Menu.class);

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert jdbcInsert;

    private final NamedParameterJdbcTemplate jdbcNamed;

    @Autowired
    public JdbcRestaurantRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate jdbcNamed) {
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("restaurants")
                .usingGeneratedKeyColumns("restaurant_id");
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcNamed = jdbcNamed;
    }


    @Override
    public Restaurant save(Restaurant restaurant) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", restaurant.getId())
                .addValue("name", restaurant.getName())
                .addValue("voteCounter", restaurant.getVoteCount());

        if (restaurant.isNew()) {
            Number id = jdbcInsert.executeAndReturnKey(map);
            restaurant.setId(id.intValue());
            restaurant.setVoteCount(0);
        } else if (jdbcNamed.update(
                "UPDATE users SET name=:name, vote_counter=:voteCounter WHERE id=:id", map) == 0) {
            return null;
        }
        return restaurant;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM restaurants WHERE id=?", id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        List<Restaurant> restaurants = jdbcTemplate.query("SELECT * FROM restaurants WHERE id=?", ROW_MAPPER_REST, id);
        return DataAccessUtils.singleResult(restaurants);
    }

    @Override
    public List<Restaurant> getAll() {
        List<Menu> menuList = jdbcTemplate.query("SELECT * FROM menu ORDER BY restaurant_id", ROW_MAPPER_MENU);
        List<Restaurant> restaurants = jdbcTemplate.query("SELECT * FROM restaurants ORDER BY id", ROW_MAPPER_REST);

        return addMenu(restaurants, menuList);
    }


    private List<Restaurant> addMenu(List<Restaurant> restaurants, List<Menu> menuList) {
        Map<String, Double> menuMap = new LinkedHashMap<>();
        int n = 0;

        for (Restaurant restaurant : restaurants) {
            for (int j = n; j < menuList.size(); j++, n++) {
                if (restaurant.getId() == menuList.get(j).getRestaurantId()) {
                    menuMap.put(menuList.get(j).getDish(), menuList.get(j).getPrice());
                } else {
                    break;
                }
            }
            restaurant.setMenuMap(menuMap);
            menuMap = new LinkedHashMap<>();
        }
        return restaurants;
    }
}