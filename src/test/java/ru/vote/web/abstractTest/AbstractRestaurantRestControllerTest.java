package ru.vote.web.abstractTest;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.vote.model.Restaurant;
import ru.vote.util.exeption.NotFoundException;
import ru.vote.web.restaurant.RestaurantRestController;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.Assert.*;
import static ru.vote.RestaurantTestData.*;

public abstract class AbstractRestaurantRestControllerTest extends AbstractRestControllerTest {

    @Autowired
    private RestaurantRestController controller;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setup() {
        cacheManager.getCache("restaurantsCache").clear();
    }

    @Test
    public void create() {
        Restaurant created = controller.create(getNew());
        Integer newId = created.getId();
        Restaurant newRestaurant = getNew();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(controller.get(newId), newRestaurant);
    }

    @Test
    public void get() {
        Restaurant restaurant = controller.get(RESTAURANT_ID);
        RESTAURANT_MATCHER.assertMatch(restaurant, restaurant1);
    }

    @Test
    public void getAll() {
        List<Restaurant> all = controller.getAll();
        RESTAURANT_MATCHER.assertMatch(all, restaurant1, restaurant2);
    }

    @Test
    public void update() {
        Restaurant updated = getUpdated();
        controller.update(updated, updated.getId());
        RESTAURANT_MATCHER.assertMatch(controller.get(RESTAURANT_ID), getUpdated());
    }

    @Test
    public void delete() {
        controller.delete(RESTAURANT_ID);
        assertThrows(NotFoundException.class, () -> controller.get(RESTAURANT_ID));
    }

    @Test
    public void incrementVoteCounter() {
        controller.incrementVoteCounter(RESTAURANT_ID);
        int incrementedVoteCounter = controller.get(RESTAURANT_ID).getVoteCounter();
        assertEquals(incrementedVoteCounter, VOTE_COUNTER + 1);
    }

    @Test
    public void getVoteCounter() {
        int gotVoteCounter = controller.getVoteCounter(RESTAURANT_ID);
        assertEquals(gotVoteCounter, VOTE_COUNTER);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> controller.get(NOT_FOUND));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> controller.delete(NOT_FOUND));
    }

    @Test
    public void createWithException() throws Exception {
        Assume.assumeTrue("Validation not supported (JPA only)", isJpaBased());
        validateRootCause(ConstraintViolationException.class, () -> controller.create(new Restaurant(null, " ", 5)));
        validateRootCause(ConstraintViolationException.class, () -> controller.create(new Restaurant(null, "Restaurant", null)));
    }
}