package ru.vote.repository;

import ru.vote.model.Menu;

import java.util.List;

public interface MenuRepository {

    boolean create(Menu menu);

    boolean update(Menu menu);

    List<Menu> getListByRestaurantId(int restaurantId);

    List<Menu> getAll();

    boolean delete(int restaurantId);
}
