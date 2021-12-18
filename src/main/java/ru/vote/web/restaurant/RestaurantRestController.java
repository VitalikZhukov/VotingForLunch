package ru.vote.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.vote.model.Restaurant;
import ru.vote.repository.RestaurantRepository;

import java.util.List;

import static ru.vote.util.ValidationUtil.checkNotFoundWithId;
import static ru.vote.util.ValidationUtil.checkNotFound;
import static ru.vote.util.ValidationUtil.checkNew;
import static ru.vote.util.ValidationUtil.assureIdConsistent;

@Controller
public class RestaurantRestController {
    private final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);

    private final RestaurantRepository restaurantRepository;

    protected RestaurantRestController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        return restaurantRepository.save(restaurant);
    }

    public Restaurant get(int id) {
        log.info("get {}", id);
        return checkNotFoundWithId(restaurantRepository.get(id), id);
    }

    public List<Restaurant> getAll() {
        log.info("getAll");
        return checkNotFound(restaurantRepository.getAll());
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {} id = {}", restaurant, id);
        assureIdConsistent(restaurant, id);
        restaurantRepository.save(restaurant);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    public void incrementVoteCounter(int id) {
        log.info("incrementVoteCounter id = {}", id);
        checkNotFoundWithId(restaurantRepository.incrementVoteCounter(id), id);
    }

    public int getVoteCounter(int id) {
        log.info("getVoteCounter {}", id);
        return checkNotFoundWithId(restaurantRepository.getVoteCounter(id), id);
    }
}
