package ru.vote;

import ru.vote.model.Restaurant;

import static ru.vote.model.AbstractModel.START_SEQ;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class);

    public static final int RESTAURANT_ID = START_SEQ + 2;
    public static final int VOTE_COUNTER = 8;
    public static final int NOT_FOUND = 10;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT_ID, "Brovar", VOTE_COUNTER);
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

    public static Restaurant getWithResetVote1 () {
        Restaurant updated = new Restaurant(restaurant1);
        updated.setId(RESTAURANT_ID);
        updated.setVoteCounter(0);
        return updated;
    }

    public static Restaurant getWithResetVote2 () {
        Restaurant updated = new Restaurant(restaurant2);
        updated.setId(RESTAURANT_ID + 1);
        updated.setVoteCounter(0);
        return updated;
    }
}
