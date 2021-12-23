package ru.vote;

import ru.vote.model.Restaurant;

import static ru.vote.model.AbstractModel.START_SEQ;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator("menu", "price");

    public static final int RESTAURANT_ID = START_SEQ + 3;
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
}
