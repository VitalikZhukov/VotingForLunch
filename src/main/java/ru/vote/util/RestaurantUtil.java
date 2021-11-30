package ru.vote.util;

import ru.vote.model.Restaurant;

import java.util.*;
import java.util.stream.Collectors;

public class RestaurantUtil {
    private static List<Restaurant> restaurants = Arrays.asList(
            new Restaurant("Лидбир", Map.of("Первое", 5.80, "Второе", 12.50, "Компот", 3.00), 10),
            new Restaurant("Друзья", Map.of("Первое", 6.30, "Второе", 15.80, "Компот", 2.85), 8),
            new Restaurant("Хопс", Map.of("Первое", 4.95, "Второе", 11.50, "Компот", 4.20), 3),
            new Restaurant("Дракон", Map.of("Первое", 5.30, "Второе", 17.80, "Компот", 2.80), 4),
            new Restaurant("ЯК-52", Map.of("Первое", 4.30, "Второе", 16.80, "Компот", 2.70), 9)
    );

    public static List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public static List<Restaurant> sortedByName() {
        return restaurants.stream()
                .sorted(Comparator.comparing(Restaurant::getName))
                .collect(Collectors.toList());
    }

    public static List<Restaurant> sortedByVoteCount() {
        return restaurants.stream()
                .sorted(Comparator.comparing(Restaurant::getVoteCount).reversed())
                .collect(Collectors.toList());
    }
}
