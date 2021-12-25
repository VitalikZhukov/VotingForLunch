package ru.vote.repository.datajpa;

import ru.vote.model.Menu;
import ru.vote.repository.MenuRepository;

import java.util.List;

public class DataJpaMenuRepository implements MenuRepository {
    private final CrudMenuRepository crudRepository;

    public DataJpaMenuRepository(CrudMenuRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Menu save(Menu menu) {
        return null;
    }

    @Override
    public Menu get(int id) {
        return null;
    }

    @Override
    public List<Menu> getListByRestaurantId(int restaurantId) {
        return null;
    }

    @Override
    public List<Menu> getAll() {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean deleteAllByRestaurantId(int restaurantId) {
        return false;
    }
}
