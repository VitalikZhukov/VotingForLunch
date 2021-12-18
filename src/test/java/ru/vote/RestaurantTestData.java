package ru.vote;

import ru.vote.model.Restaurant;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class RestaurantTestData {
    public static final int RESTAURANT_ID = 1;
    public static final int VOTE_COUNTER = 8;
    public static final int NOT_FOUND = 10;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT_ID, "Brovar", 8);
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT_ID + 1, "Lidbeer", 15);

    public static Restaurant getNew() {
        return new Restaurant(null, "newRestaurant", 99);
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(restaurant1);
        updated.setId(RESTAURANT_ID);
        updated.setName("updatedName");
        updated.setVoteCounter(1000);
        return updated;
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("menu", "price").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparatorIgnoringFields("menu", "price").isEqualTo(expected);
    }

    public static void assertMatch(Integer actual, Integer expected) {
        assertEquals(actual, expected);
    }
}
