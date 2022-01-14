package ru.vote.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.vote.model.User;
import ru.vote.repository.UserRepository;
import ru.vote.to.UserTo;
import ru.vote.util.UserUtil;

import java.util.List;

import static ru.vote.util.ValidationUtil.*;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "usersCache", allEntries = true)
    public User create(User user) {
        return repository.save(user);
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User getByEmail(String email) {
        return checkNotFound(repository.getByEmail(email));
    }

    @Cacheable("usersCache")
    public List<User> getAll() {
        return checkNotFound(repository.getAll());
    }

    @CacheEvict(value = "usersCache", allEntries = true)
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
//      checkNotFoundWithId : check works only for JDBC, disabled
        repository.save(user);
    }

    @CacheEvict(value = "usersCache", allEntries = true)
    @Transactional
    public void update(UserTo userTo) {
        User user = get(userTo.id());
        User updatedUser = UserUtil.updateFromTo(user, userTo);
        repository.save(updatedUser);   // !! need only for JDBC implementation
    }

    @CacheEvict(value = "usersCache", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        repository.save(user);  // !! need only for JDBC implementation
    }
}
