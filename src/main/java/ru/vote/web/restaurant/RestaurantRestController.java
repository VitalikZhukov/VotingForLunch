package ru.vote.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.vote.model.Restaurant;
import ru.vote.service.RestaurantService;

import java.util.List;

import static ru.vote.util.ValidationUtil.checkNotFoundWithId;
import static ru.vote.util.ValidationUtil.checkNotFound;
import static ru.vote.util.ValidationUtil.checkNew;
import static ru.vote.util.ValidationUtil.assureIdConsistent;

@Controller
public class RestaurantRestController {
    private final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);

    private final RestaurantService restaurantService;

    protected RestaurantRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        return restaurantService.create(restaurant);
    }

    public Restaurant get(int id) {
        log.info("get {}", id);
        return restaurantService.get(id);
    }

    public List<Restaurant> getAll() {
        log.info("getAll");
        return restaurantService.getAll();
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {} id = {}", restaurant, id);
        restaurantService.update(restaurant, id);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        restaurantService.delete(id);
    }

    public void incrementVoteCounter(int id) {
        log.info("incrementVoteCounter id = {}", id);
        restaurantService.incrementVoteCounter(id);
    }

    public int getVoteCounter(int id) {
        log.info("getVoteCounter {}", id);
        return restaurantService.getVoteCounter(id);
    }
}
