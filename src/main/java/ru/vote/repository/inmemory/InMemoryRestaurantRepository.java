package ru.vote.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.vote.model.Restaurant;
import ru.vote.repository.RestaurantRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryRestaurantRepository implements RestaurantRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryRestaurantRepository.class);

    // ID foe DB in memory
    private final AtomicInteger inMemoryRestaurantID = new AtomicInteger(0);

    // DB in memory
    private final Map<Integer, Restaurant> inMemoryRestaurantDB = new ConcurrentHashMap<>();

    @Override
    public Restaurant save(Restaurant restaurant) {
        log.info("save {}", restaurant);
        if (restaurant.isNew()) {
            restaurant.setId(inMemoryRestaurantID.incrementAndGet());
            inMemoryRestaurantDB.put(restaurant.getId(), restaurant);
            return restaurant;
        }
        //update
        //action with value in Map, if the value there
        return inMemoryRestaurantDB.computeIfPresent(restaurant.getId(), (id, oldRestaurant) -> restaurant);
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return inMemoryRestaurantDB.remove(id) != null;
    }

    @Override
    public Restaurant get(int id) {
        log.info("get {}", id);
        return inMemoryRestaurantDB.get(id);
    }

    @Override
    public List<Restaurant> getAll() {
        log.info("getAll");
        return inMemoryRestaurantDB.values().stream()
                .sorted(Comparator.comparing(Restaurant::getVoteCount).reversed().thenComparing(Restaurant::getName))
                .collect(Collectors.toList());
    }
}
