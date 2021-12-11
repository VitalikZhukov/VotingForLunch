package ru.vote.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vote.model.Menu;
import ru.vote.repository.MenuRepository;

import java.util.List;

@Repository
public class JdbcMenuRepository implements MenuRepository {
    private static final BeanPropertyRowMapper<Menu> ROW_MAPPER_MENU = BeanPropertyRowMapper.newInstance(Menu.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcMenuRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(Menu menu) {
        return jdbcTemplate.update("UPDATE menu SET restaurant_id=?, dish=?, price=?",
                menu.getRestaurantId(), menu.getDish(), menu.getPrice()) != 0;
    }

    @Override
    public boolean update(Menu menu) {
        return jdbcTemplate.update("UPDATE menu SET dish=?, price=? WHERE restaurant_id=?",
                menu.getDish(), menu.getPrice(), menu.getRestaurantId()) != 0;
    }

    @Override
    public List<Menu> getListByRestaurantId(int restaurantId) {
        return jdbcTemplate.query("SELECT * FROM menu WHERE id=?", ROW_MAPPER_MENU, restaurantId);
    }

    @Override
    public List<Menu> getAll() {
        return jdbcTemplate.query("SELECT * FROM menu ORDER BY restaurant_id", ROW_MAPPER_MENU);
    }

    @Override
    public boolean delete(int restaurantId) {
        return jdbcTemplate.update("DELETE * FROM menu WHERE restaurant_id=?", restaurantId) != 0;

    }

}
