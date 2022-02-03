package ru.vote.web.json;

import org.junit.jupiter.api.Test;
import ru.vote.UserTestData;
import ru.vote.model.Restaurant;
import ru.vote.model.User;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void writeOnlyAccess() {
        String json = JsonUtil.writeValue(UserTestData.user);
        System.out.println(json);
        assertThat(json, not(containsString("password")));
        String jsonWithPass = UserTestData.jsonWithPassword(UserTestData.user, "NewPass");
        System.out.println(jsonWithPass);
        User user = JsonUtil.readValue(jsonWithPass, User.class);
        assertEquals(user.getPassword(), "NewPass");
    }
}