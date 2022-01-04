package ru.vote.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.vote.model.User;
import ru.vote.repository.UserRepository;

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
        checkNew(user);
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
        checkNotFoundWithId(repository.save(user), user.id());
    }

    @CacheEvict(value = "usersCache", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }
}
