package ru.vote.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.vote.model.Menu;
import ru.vote.repository.MenuRepository;

import java.util.List;

@Repository
public class DataJpaMenuRepository implements MenuRepository {
    private static final Sort SORT_ID = Sort.by(Sort.Direction.ASC, "id");

    private final CrudMenuRepository crudRepository;

    public DataJpaMenuRepository(CrudMenuRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Menu save(Menu menu) {
        return crudRepository.save(menu);
    }

    @Override
    public Menu get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Menu> getListByRestaurantId(int restaurantId) {
        return crudRepository.getListByRestaurantId(restaurantId);
    }

    @Override
    public List<Menu> getAll() {
        return crudRepository.findAll(SORT_ID);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public boolean deleteAllByRestaurantId(int restaurantId) {
        return crudRepository.deleteAllByRestaurantId(restaurantId) != 0;
    }
}
