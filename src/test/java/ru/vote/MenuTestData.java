package ru.vote;

import ru.vote.model.Menu;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuTestData {
    public static final int MENU_ID = 1;

    public static final Menu menu = new Menu(MENU_ID, "Dish1", 99.99);

    public static final Menu menu1 = new Menu(MENU_ID, 1, "First", 12.5);
    public static final Menu menu2 = new Menu(MENU_ID + 1, 1, "Second", 14.8);
    public static final Menu menu3 = new Menu(MENU_ID + 2, 1, "Third", 2.98);

    public static final Menu menu4 = new Menu(MENU_ID + 3, 2, "First", 4.5);
    public static final Menu menu5 = new Menu(MENU_ID + 4, 2, "Second", 8.8);
    public static final Menu menu6 = new Menu(MENU_ID + 5, 2, "Third", 2.85);

    public static Menu getNew() {
        return new Menu(null,2, "NewDish", 55.55);
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