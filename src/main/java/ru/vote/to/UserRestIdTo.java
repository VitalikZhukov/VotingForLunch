package ru.vote.to;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vote.service.UserService;

public class UserRestIdTo {

    @Autowired
    public UserService userService;

    public void setRestaurantId(int userId, int restaurantId) {
        userService.setRestaurantId(userId, restaurantId);
    }

}
