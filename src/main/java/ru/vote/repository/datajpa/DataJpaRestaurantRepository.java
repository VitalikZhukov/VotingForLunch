package ru.vote.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.vote.model.Restaurant;
import ru.vote.repository.RestaurantRepository;

import java.util.List;

@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository {
    private static final Sort SORT_ID = Sort.by(Sort.Direction.ASC, "id");

    private final CrudRestaurantRepository crudRepository;

    public DataJpaRestaurantRepository(CrudRestaurantRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return crudRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRepository.findAll(SORT_ID);
    }

    @Override
    public boolean incrementVoteCounter(int id) {
        int counter = getVoteCounter(id);
        counter++;
        return crudRepository.incrementVoteCounter(counter, id) != 0;
    }

    @Override
    public int getVoteCounter(int id) {
        return crudRepository.getVoteCounter(id);
    }
}
