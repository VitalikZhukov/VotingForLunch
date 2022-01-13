package ru.vote.util;

import ru.vote.model.Role;
import ru.vote.model.User;
import ru.vote.to.UserTo;

public class UserUtil {

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getLogin(), userTo.getEmail().toLowerCase(), userTo.getPassword(), userTo.getRestaurantId(), Role.USER);
    }
}