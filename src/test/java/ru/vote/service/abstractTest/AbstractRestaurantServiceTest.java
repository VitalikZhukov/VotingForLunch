package ru.vote.service.abstractTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.vote.model.Restaurant;
import ru.vote.service.RestaurantService;
import ru.vote.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.vote.RestaurantTestData.*;

public abstract class AbstractRestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    public void setup() {
        cacheManager.getCache("restaurantsCache").clear();
    }

    @Test
    void create() {
        Restaurant created = restaurantService.create(getNew());
        Integer newId = created.getId();
        Restaurant newRestaurant = getNew();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantService.get(newId), newRestaurant);
    }

    @Test
    void get() {
        Restaurant restaurant = restaurantService.get(RESTAURANT_ID);
        RESTAURANT_MATCHER.assertMatch(restaurant, restaurant1);
    }

    @Test
    void getAll() {
        List<Restaurant> all = restaurantService.getAll();
        RESTAURANT_MATCHER.assertMatch(all, restaurant1, restaurant2);
    }

    @Test
    void update() {
        Restaurant updated = getUpdated();
        restaurantService.update(updated, updated.getId());
        RESTAURANT_MATCHER.assertMatch(restaurantService.get(RESTAURANT_ID), getUpdated());
    }

    @Test
    void resetAllVoteCounter() {
        restaurantService.resetAllVoteCounter();
        RESTAURANT_MATCHER.assertMatch(restaurantService.getAll(), getWithResetVote1(), getWithResetVote2());
    }

    @Test
    void delete() {
        restaurantService.delete(RESTAURANT_ID);
        assertThrows(NotFoundException.class, () -> restaurantService.get(RESTAURANT_ID));
    }

    @Test
    void incrementVoteCounter() {
        restaurantService.incrementVoteCounter(RESTAURANT_ID);
        int incrementedVoteCounter = restaurantService.get(RESTAURANT_ID).getVoteCounter();
        assertEquals(incrementedVoteCounter, VOTE_COUNTER + 1);
    }

    @Test
    void getVoteCounter() {
        int gotVoteCounter = restaurantService.getVoteCounter(RESTAURANT_ID);
        assertEquals(gotVoteCounter, VOTE_COUNTER);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> restaurantService.get(NOT_FOUND));
    }

    @Test
    void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> restaurantService.delete(NOT_FOUND));
    }

    @Test
    void createWithException() throws Exception {
        validateRootCause(ConstraintViolationException.class, () -> restaurantService.create(new Restaurant(null, " ", 5)));
//        voteCounter must be null (temporarily???)
//        validateRootCause(ConstraintViolationException.class, () -> restaurantService.create(new Restaurant(null, "Restaurant", null)));
    }
}