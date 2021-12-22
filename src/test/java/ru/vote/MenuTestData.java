package ru.vote;

import ru.vote.model.Menu;

import static ru.vote.model.AbstractModel.START_SEQ;

public class MenuTestData {
    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator("");

    public static final int MENU_ID = START_SEQ;
    public static final int RESTAURANT_ID = START_SEQ;
    public static final int NOT_FOUND = 10;

    public static final Menu menu = new Menu(MENU_ID, "Dish1", 99.99);

    public static final Menu menu1 = new Menu(MENU_ID, 10000, "First", 12.5);
    public static final Menu menu2 = new Menu(MENU_ID + 1, 10000, "Second", 14.8);
    public static final Menu menu3 = new Menu(MENU_ID + 2, 10000, "Third", 2.98);

    public static final Menu menu4 = new Menu(MENU_ID + 3, 10001, "First", 4.5);
    public static final Menu menu5 = new Menu(MENU_ID + 4, 10001, "Second", 8.8);
    public static final Menu menu6 = new Menu(MENU_ID + 5, 10001, "Third", 2.85);

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
}