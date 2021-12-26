package ru.vote.service;

import org.springframework.stereotype.Service;
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

    public List<User> getAll() {
        return checkNotFound(repository.getAll());
    }

    public void update(User user, int id) {
        assureIdConsistent(user, id);
        repository.save(user);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }
}
