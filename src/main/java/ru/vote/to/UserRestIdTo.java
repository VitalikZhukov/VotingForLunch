package ru.vote.to;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vote.service.UserService;
import ru.vote.util.TimeControl;
import ru.vote.util.exeption.VoteException;

public class UserRestIdTo {

    private boolean vote = false;

    @Autowired
    public UserService userService;

    @Autowired
    public TimeControl timeControl;

    public void setRestaurantId(int userId, int restaurantId) {
        timeControl.start();
        if (!vote) {
            userService.setRestaurantId(userId, restaurantId);
            vote = true;
        } else {
            throw new VoteException();
        }

    }
}
