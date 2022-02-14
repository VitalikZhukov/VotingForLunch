package ru.vote.to;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vote.service.UserService;
import ru.vote.util.exception.VoteException;

public class UserRestIdTo {

    @Autowired
    public UserService userService;

    public void setRestaurantId(int userId, int restaurantId) {
        Integer restId = userService.getRestaurantId(userId);
        if (restId == null || restId == 0) {
            userService.setRestaurantId(userId, restaurantId);
        } else {
            throw new VoteException("You can vote once a day");
        }

    }
}
