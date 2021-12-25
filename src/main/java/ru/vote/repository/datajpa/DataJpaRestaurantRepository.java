package ru.vote.repository.datajpa;

import ru.vote.model.Restaurant;
import ru.vote.repository.RestaurantRepository;

import java.util.List;

public class DataJpaRestaurantRepository implements RestaurantRepository {
    private final CrudRestaurantRepository crudRepository;

    public DataJpaRestaurantRepository(CrudRestaurantRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Restaurant get(int id) {
        return null;
    }

    @Override
    public List<Restaurant> getAll() {
        return null;
    }

    @Override
    public boolean incrementVoteCounter(int id) {
        return false;
    }

    @Override
    public int getVoteCounter(int id) {
        return 0;
    }
}
