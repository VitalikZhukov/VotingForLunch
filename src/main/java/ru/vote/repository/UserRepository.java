package ru.vote.repository;

import ru.vote.model.User;

import java.util.List;

public interface UserRepository {
    // null if not found, when updated
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();

    boolean setRestaurantId (int userId, int restaurantId);

    void resetAllRestaurantId();

    Integer getRestaurantId(int id);

}
