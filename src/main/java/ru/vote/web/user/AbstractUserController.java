package ru.vote.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vote.model.User;
import ru.vote.service.UserService;

import java.util.List;


public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    public User create(User user) {
        log.info("create {}", user);
        return userService.create(user);
    }

    public User get(int id) {
        log.info("get {}", id);
        return userService.get(id);
    }

    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return userService.getByEmail(email);
    }

    public List<User> getAll() {
        log.info("getAll");
        return userService.getAll();
    }

    public void update(User user, int id) {
        log.info("update {} id = {}", user, id);
        userService.update(user, id);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        userService.delete(id);
    }

}
