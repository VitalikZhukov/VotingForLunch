package ru.vote;

import ru.vote.model.Menu;

import static ru.vote.model.AbstractModel.START_SEQ;

public class MenuTestData {
    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Menu.class, "");

    public static final int MENU_ID = START_SEQ + 5;
    public static final int RESTAURANT_ID = START_SEQ + 3;
    public static final int NOT_FOUND = 10;

    public static final Menu menu = new Menu(MENU_ID, "Dish1", 99.99);

    public static final Menu menu1 = new Menu(MENU_ID, RESTAURANT_ID, "First", 12.5);
    public static final Menu menu2 = new Menu(MENU_ID + 1, RESTAURANT_ID, "Second", 14.8);
    public static final Menu menu3 = new Menu(MENU_ID + 2, RESTAURANT_ID, "Third", 2.98);

    public static final Menu menu4 = new Menu(MENU_ID + 3, RESTAURANT_ID + 1, "First", 4.5);
    public static final Menu menu5 = new Menu(MENU_ID + 4, RESTAURANT_ID + 1, "Second", 8.8);
    public static final Menu menu6 = new Menu(MENU_ID + 5, RESTAURANT_ID + 1, "Third", 2.85);

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