package ru.vote.util;

import org.slf4j.Logger;
import ru.vote.model.Restaurant;

import java.util.*;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class RestaurantUtil {
    private static final Logger log = getLogger(RestaurantUtil.class);

    private static List<Restaurant> restaurants = Arrays.asList(
            new Restaurant(1,"Лидбир", Map.of("Первое", 5.80, "Второе", 12.50, "Компот", 3.00), 10),
            new Restaurant(2,"Друзья", Map.of("Первое", 6.30, "Второе", 15.80, "Компот", 2.85), 8),
            new Restaurant(3,"Хопс", Map.of("Первое", 4.95, "Второе", 11.50, "Компот", 4.20), 3),
            new Restaurant(4,"Дракон", Map.of("Первое", 5.30, "Второе", 17.80, "Компот", 2.80), 4),
            new Restaurant(5,"ЯК-52", Map.of("Первое", 4.30, "Второе", 16.80, "Компот", 2.70), 9)
    );

    public static List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public static List<Restaurant> getSortedByNameList() {
        log.info("--Sorted by name.--");
        return restaurants.stream()
                .sorted(Comparator.comparing(Restaurant::getName))
                .collect(Collectors.toList());
    }

    public static List<Restaurant> getSortedByVoteCountList() {
        log.info("--Sorted by vote count.--");
        return restaurants.stream()
                .sorted(Comparator.comparing(Restaurant::getVoteCount).reversed())
                .collect(Collectors.toList());
    }
}
