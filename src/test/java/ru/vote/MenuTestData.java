package ru.vote;

import ru.vote.model.Menu;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuTestData {
    public static final int MENU_ID = 1;

    public static final Menu menu = new Menu(MENU_ID, "Dish1", 99.99);

    public static Menu getNew() {
        return new Menu(3,2, "NewDish", 55.55);
    }

    public static Menu getUpdated() {
        Menu updated = new Menu(menu);
        updated.setId(MENU_ID);
        updated.setRestaurantId(MENU_ID);
        updated.setDish("updatedDish");
        updated.setPrice(10000);
        return updated;
    }

    public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Menu> actual, Menu... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Menu> actual, Iterable<Menu> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparatorIgnoringFields().isEqualTo(expected);
    }

}
