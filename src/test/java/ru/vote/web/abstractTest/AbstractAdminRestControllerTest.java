package ru.vote.web.abstractTest;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.vote.UserTestData;
import ru.vote.model.Role;
import ru.vote.model.User;
import ru.vote.util.exeption.NotFoundException;
import ru.vote.web.user.AdminRestController;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static ru.vote.UserTestData.*;
import static ru.vote.UserTestData.NOT_FOUND;

public abstract class AbstractAdminRestControllerTest extends AbstractRestControllerTest {

    @Autowired
    private AdminRestController controller;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setup() {
        cacheManager.getCache("usersCache").clear();
    }

    @Test
    public void create() {
        User created = controller.create(getNew());
        Integer newId = created.getId();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(controller.get(newId), newUser);
    }

    @Test
    public void update() {
        User updated = getUpdated();
        controller.update(updated, updated.getId());
        USER_MATCHER.assertMatch(controller.get(USER_ID), getUpdated());
    }

    @Test
    public void delete() {
        controller.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> controller.get(USER_ID));
    }

    @Test
    public void get() {
        User user = controller.get(USER_ID);
        USER_MATCHER.assertMatch(user, UserTestData.user);
    }

    @Test
    public void getByEmail() {
        User user = controller.getByEmail("admin@tut.by");
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
    public void getAll() {
        List<User> all = controller.getAll();
        USER_MATCHER.assertMatch(all, admin, user, user2);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> controller.get(NOT_FOUND));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> controller.delete(NOT_FOUND));
    }

    @Test
    public void createWithException() throws Exception {
        Assume.assumeTrue("Validation not supported (JPA only)", isJpaBased());
        validateRootCause(ConstraintViolationException.class, () -> controller.create(new User(null, " ", "mail@tut.by", "password", 10000, Role.USER)));
        validateRootCause(ConstraintViolationException.class, () -> controller.create(new User(null, "User", " ", "password", 10000, Role.USER)));
        validateRootCause(ConstraintViolationException.class, () -> controller.create(new User(null, "User", "mail@tut.by", " ", 10000, Role.USER)));
    }
}