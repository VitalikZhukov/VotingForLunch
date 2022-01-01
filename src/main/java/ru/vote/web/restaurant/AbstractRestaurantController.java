package ru.vote.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vote.model.Restaurant;
import ru.vote.service.RestaurantService;

import java.util.List;

import static ru.vote.util.ValidationUtil.checkNotFoundWithId;

public abstract class AbstractRestaurantController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    public Restaurant create(Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        return restaurantService.create(restaurant);
    }

    public Restaurant get(int id) {
        log.info(("get restaurant {}"), id);
        return restaurantService.get(id);
    }

    public List<Restaurant> getAll() {
        log.info("getAll restaurants");
        return restaurantService.getAll();
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update restaurant {} for id = {}", restaurant, id);
        restaurantService.update(restaurant, id);
    }

    public void delete(int id) {
        log.info("delete restaurant {}", id);
        restaurantService.delete(id);
    }

    public void incrementVoteCounter(int id) {
        log.info("increment vote counter {}", id);
        restaurantService.incrementVoteCounter(id);
    }

    public int getVoteCounter(int id) {
        log.info("get vote counter {}", id);
        return restaurantService.getVoteCounter(id);
    }

}
