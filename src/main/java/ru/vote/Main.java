package ru.vote;

import ru.vote.model.Restaurant;
import ru.vote.util.RestaurantUtil;

public class Main {
    public static void main(String[] args) {

        for (Restaurant rest : RestaurantUtil.getRestaurants()) {
            System.out.println(rest.getName() + " " + rest.getVoteCount());
        }

        for (Restaurant rest : RestaurantUtil.getSortedByNameList()) {
            System.out.println(rest.getName());
        }

        for (Restaurant rest : RestaurantUtil.getSortedByVoteCountList()) {
            System.out.println(rest.getVoteCount());
        }
    }
}
