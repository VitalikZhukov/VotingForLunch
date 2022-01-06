package ru.vote.web.json;

import org.junit.jupiter.api.Test;
import ru.vote.model.Restaurant;

import java.util.List;

import static ru.vote.RestaurantTestData.*;

class JsonUtilTest {

    @Test
    void readWriteValue() {
        String json = JsonUtil.writeValue(restaurant1);
        System.out.println(json);
        Restaurant meal = JsonUtil.readValue(json, Restaurant.class);
        RESTAURANT_MATCHER.assertMatch(meal, restaurant1);
    }

    @Test
    void readWriteValues() {
        final List<Restaurant> restaurantList = List.of(restaurant1, restaurant2);
        String json = JsonUtil.writeValue(restaurantList);
        System.out.println(json);
        List<Restaurant> restaurants = JsonUtil.readValues(json, Restaurant.class);
        RESTAURANT_MATCHER.assertMatch(restaurants, restaurantList);
    }
}