package ru.vote.repository;

import ru.vote.model.Menu;

import java.util.List;

public interface MenuRepository {

    Menu save(Menu menu);

    Menu get(int id);

    List<Menu> getListByRestaurantId(int restaurantId);

    List<Menu> getAll();

    boolean delete(int restaurantId);
}
