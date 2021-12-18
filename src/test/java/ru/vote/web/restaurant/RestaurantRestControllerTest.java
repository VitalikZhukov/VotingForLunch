package ru.vote.web.restaurant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vote.model.Restaurant;
import ru.vote.util.exeption.NotFoundException;

import java.util.List;

import static org.junit.Assert.*;
import static ru.vote.RestaurantTestData.*;

@ContextConfiguration({
        "classpath:spring/spring.xml",
        "classpath:spring/springDB.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/initDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantRestControllerTest {

    @Autowired
    private RestaurantRestController controller;

    @Test
    public void create() {
        Restaurant created = controller.create(getNew());
        Integer newId = created.getId();
        Restaurant newRestaurant = getNew();
        newRestaurant.setId(newId);
        assertMatch(created, newRestaurant);
        assertMatch(controller.get(newId), newRestaurant);
    }

    @Test
    public void get() {
        Restaurant restaurant = controller.get(RESTAURANT_ID);
        assertMatch(restaurant, restaurant1);
    }

    @Test
    public void getAll() {
        List<Restaurant> all = controller.getAll();
        assertMatch(all, restaurant1, restaurant2);
    }

    @Test
    public void update() {
        Restaurant updated = getUpdated();
        controller.update(updated, updated.getId());
        assertMatch(controller.get(RESTAURANT_ID), getUpdated());
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
        assertMatch(incrementedVoteCounter, VOTE_COUNTER + 1);
    }

    @Test
    public void getVoteCounter() {
        int gotVoteCounter = controller.get(RESTAURANT_ID).getVoteCounter();
        assertMatch(gotVoteCounter, VOTE_COUNTER);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> controller.get(NOT_FOUND));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> controller.delete(NOT_FOUND));
    }
}