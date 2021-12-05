package ru.vote.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vote.model.User;
import ru.vote.repository.UserRepository;

import java.util.List;

import static ru.vote.util.ValidationUtil.checkNotFoundWithId;
import static ru.vote.util.ValidationUtil.checkNotFound;
import static ru.vote.util.ValidationUtil.checkNew;
import static ru.vote.util.ValidationUtil.assureIdConsistent;


public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    protected AbstractUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return userRepository.save(user);
    }

    public User get(int id) {
        log.info("get {}", id);
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return checkNotFound(userRepository.getByEmail(email));
    }

    public List<User> getAll() {
        log.info("getAll");
        return checkNotFound(userRepository.getAll());
    }

    public void update(User user, int id) {
        log.info("update {} id = {}", user, id);
        assureIdConsistent(user, id);
        userRepository.save(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        checkNotFoundWithId(userRepository.delete(id), id);
    }

}
