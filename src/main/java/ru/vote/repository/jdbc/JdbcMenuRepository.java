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
import ru.vote.model.Menu;
import ru.vote.repository.MenuRepository;
import ru.vote.util.ValidationUtil;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JdbcMenuRepository implements MenuRepository {
    private static final BeanPropertyRowMapper<Menu> ROW_MAPPER_MENU = BeanPropertyRowMapper.newInstance(Menu.class);

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert jdbcInsert;

    private final NamedParameterJdbcTemplate jdbcNamed;

    @Autowired
    public JdbcMenuRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate jdbcNamed) {
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("menu")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcNamed = jdbcNamed;
    }

    @Override
    @Transactional
    public Menu save(Menu menu) {
        ValidationUtil.validate(menu);

        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", menu.getId())
                .addValue("restaurant_id", menu.getRestaurantId())
                .addValue("dish", menu.getDish())
                .addValue("price", menu.getPrice());

        if (menu.isNew()) {
            Number id = jdbcInsert.executeAndReturnKey(map);
            menu.setId(id.intValue());
        } else if (jdbcNamed.update(
                "UPDATE menu SET restaurant_id=:restaurant_id, dish=:dish, price=:price WHERE id=:id", map) == 0) {
            return null;
        }
        return menu;
    }

    @Override
    public Menu get(int id) {
        List<Menu> menu = jdbcTemplate.query("SELECT * FROM menu WHERE id=?", ROW_MAPPER_MENU, id);
        return DataAccessUtils.singleResult(menu);
    }

    @Override
    public List<Menu> getListByRestaurantId(int restaurantId) {
        return jdbcTemplate.query("SELECT * FROM menu WHERE restaurant_id=?", ROW_MAPPER_MENU, restaurantId);
    }

    @Override
    public List<Menu> getAll() {
        return jdbcTemplate.query("SELECT * FROM menu ORDER BY restaurant_id", ROW_MAPPER_MENU);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM menu WHERE id=?", id) != 0;
    }

    @Override
    @Transactional
    public boolean deleteAllByRestaurantId(int restaurantId) {
        return jdbcTemplate.update("DELETE FROM menu WHERE restaurant_id=?", restaurantId) != 0;
    }

}