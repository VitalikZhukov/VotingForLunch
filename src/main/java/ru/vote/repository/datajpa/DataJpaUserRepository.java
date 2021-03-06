package ru.vote.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.vote.model.User;
import ru.vote.repository.UserRepository;


import java.util.List;

@Repository
public class DataJpaUserRepository implements UserRepository {
    private static final Sort SORT_LOGIN_EMAIL = Sort.by(Sort.Direction.ASC, "login", "email");

    private final CrudUserRepository crudRepository;

    public DataJpaUserRepository(CrudUserRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public User save(User user) {
        return crudRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return crudRepository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return crudRepository.findAll(SORT_LOGIN_EMAIL);
    }

    @Override
    public boolean setRestaurantId(int userId, int restaurantId) {
        return crudRepository.setRestaurantId(userId, restaurantId) != 0;
    }

    @Override
    public void resetAllRestaurantId() {
        crudRepository.resetAllRestaurantId();
    }

    @Override
    public Integer getRestaurantId(int id) {
        return get(id).getRestaurantId();
    }
}
