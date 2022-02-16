package ru.vote.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vote.model.Restaurant;
import ru.vote.to.UserRestIdTo;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@ApiIgnore
@RestController
@RequestMapping(value = "/profile/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantUIController extends AbstractRestaurantController{

    @Autowired
    private UserRestIdTo userRestIdTo;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(@Valid Restaurant restaurant) {
        restaurant.setVoteCounter(0);
        if (restaurant.isNew()) {
            super.create(restaurant);
        } else {
            super.update(restaurant, restaurant.getId());
        }
    }

    @Override
    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void incrementVoteCounter(@PathVariable String id) {
        String[] data = id.split("!");
        int restaurantId = Integer.parseInt(data[0]);
        int userId = Integer.parseInt(data[1]);
        userRestIdTo.setRestaurantId(userId, restaurantId);
        super.incrementVoteCounter(restaurantId);
    }



}
