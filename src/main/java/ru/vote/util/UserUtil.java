package ru.vote.util;

import ru.vote.model.Role;
import ru.vote.model.User;
import ru.vote.to.UserTo;

public class UserUtil {

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getLogin(), userTo.getEmail().toLowerCase(), userTo.getPassword(), userTo.getRestaurantId(), Role.USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setLogin(userTo.getLogin());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        user.setRestaurantId(userTo.getRestaurantId());
        return user;
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getLogin(), user.getEmail(), user.getPassword(), user.getRestaurantId());
    }
}