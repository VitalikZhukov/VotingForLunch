package ru.vote.service.abstractTest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.vote.model.Role;
import ru.vote.model.User;
import ru.vote.service.UserService;
import ru.vote.util.exeption.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.Assert.*;
import static ru.vote.UserTestData.*;
import static ru.vote.UserTestData.NOT_FOUND;

public abstract class AbstractUserServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setup() {
        cacheManager.getCache("usersCache").clear();
    }

    @Test
    public void create() {
        User created = userService.create(getNew());
        Integer newId = created.getId();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(userService.get(newId), newUser);
    }

    @Test
    public void update() {
        User updated = getUpdated();
        userService.update(updated);
        USER_MATCHER.assertMatch(userService.get(USER_ID), getUpdated());
    }

    @Test
    public void delete() {
        userService.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> userService.get(USER_ID));
    }

    @Test
    public void get() {
        User user = userService.get(ADMIN_ID);
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
    public void getByEmail() {
        User user = userService.getByEmail("admin@tut.by");
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
    public void getAll() {
        List<User> all = userService.getAll();
        USER_MATCHER.assertMatch(all, admin, user, user2);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> userService.get(NOT_FOUND));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> userService.delete(NOT_FOUND));
    }

    @Test
    public void createWithException() throws Exception {
        validateRootCause(ConstraintViolationException.class, () -> userService.create(new User(null, " ", "mail@tut.by", "password", 10000, Role.USER)));
        validateRootCause(ConstraintViolationException.class, () -> userService.create(new User(null, "User", " ", "password", 10000, Role.USER)));
        validateRootCause(ConstraintViolationException.class, () -> userService.create(new User(null, "User", "mail@tut.by", " ", 10000, Role.USER)));
    }
}