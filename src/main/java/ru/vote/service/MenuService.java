package ru.vote.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.vote.model.Menu;
import ru.vote.repository.MenuRepository;

import java.util.List;

import static ru.vote.util.ValidationUtil.*;
import static ru.vote.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {
    private final MenuRepository repository;

    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "menuCache", allEntries = true)
    public Menu create(Menu menu) {
        checkNew(menu);
        return repository.save(menu);
    }

    public Menu get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Menu> getListByRestaurantId(int restaurantId) {
        return checkNotFound(repository.getListByRestaurantId(restaurantId));
    }

//    @Cacheable("menuCache")
    public List<Menu> getAll() {
        return checkNotFound(repository.getAll());
    }

    @CacheEvict(value = "menuCache", allEntries = true)
    public void update(Menu menu, int id) {
        assureIdConsistent(menu, id);
        repository.save(menu);
    }

    @CacheEvict(value = "menuCache", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void deleteAllByRestaurantId (int restaurantId) {
        checkNotFoundWithId(repository.deleteAllByRestaurantId(restaurantId), restaurantId);
    }
}
