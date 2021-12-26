package ru.vote.service;

import org.springframework.stereotype.Service;
import ru.vote.model.Restaurant;
import ru.vote.repository.RestaurantRepository;

import java.util.List;

import static ru.vote.util.ValidationUtil.*;
import static ru.vote.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {
    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant create(Restaurant restaurant) {
        checkNew(restaurant);
        return repository.save(restaurant);
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Restaurant> getAll() {
        return checkNotFound(repository.getAll());
    }

    public void update(Restaurant restaurant, int id) {
        assureIdConsistent(restaurant, id);
        repository.save(restaurant);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void incrementVoteCounter(int id) {
        checkNotFoundWithId(repository.incrementVoteCounter(id), id);
    }

    public int getVoteCounter(int id) {
        return checkNotFoundWithId(repository.getVoteCounter(id), id);
    }
}
