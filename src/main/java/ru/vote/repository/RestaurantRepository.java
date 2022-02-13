package ru.vote.repository;

import ru.vote.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    // null if not found, when updated
    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    List<Restaurant> getAll();

    boolean incrementVoteCounter(int id);

    int getVoteCounter(int id);

    void resetAllRestaurantId();
}
