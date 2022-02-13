package ru.vote.service.abstractTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.vote.model.Role;
import ru.vote.model.User;
import ru.vote.service.UserService;
import ru.vote.util.exeption.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.vote.UserTestData.*;

public abstract class AbstractUserServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    public void setup() {
        cacheManager.getCache("usersCache").clear();
    }

    @Test
    void create() {
        User created = userService.create(getNew());
        Integer newId = created.getId();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(userService.get(newId), newUser);
    }

    @Test
    void update() {
        User updated = getUpdated();
        userService.update(updated);
        USER_MATCHER.assertMatch(userService.get(USER_ID), getUpdated());
    }

    @Test
    void resetAllRestaurantId() {
        userService.resetAllRestaurantId();
        USER_MATCHER.assertMatch(userService.getAll(), getWithResetRestID2(), getWithResetRestID1());
    }

    @Test
    void getRestaurantId() {
        userService.setRestaurantId(USER_ID, WITH_REST_ID);
        Integer restaurantId = userService.getRestaurantId(USER_ID);
        assertEquals(restaurantId, WITH_REST_ID);
    }

    @Test
    void setRestaurantId() {
        userService.setRestaurantId(USER_ID, WITH_REST_ID);
        USER_MATCHER.assertMatch(userService.get(USER_ID), getWithRestaurantId());
    }

    @Test
    void delete() {
        userService.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> userService.get(USER_ID));
    }

    @Test
    void get() {
        User user = userService.get(ADMIN_ID);
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
    void getByEmail() {
        User user = userService.getByEmail("admin@tut.by");
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
    void getAll() {
        List<User> all = userService.getAll();
        USER_MATCHER.assertMatch(all, admin, user);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> userService.get(NOT_FOUND));
    }

    @Test
    void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> userService.delete(NOT_FOUND));
    }

    @Test
    void createWithException() throws Exception {
        validateRootCause(ConstraintViolationException.class, () -> userService.create(new User(null, " ", "mail@tut.by", "password", 10000, Role.USER)));
        validateRootCause(ConstraintViolationException.class, () -> userService.create(new User(null, "User", " ", "password", 10000, Role.USER)));
    }

    @Test
    void enable() {
        userService.enable(USER_ID, false);
        assertFalse(userService.get(USER_ID).isEnabled());
        userService.enable(USER_ID, true);
        assertTrue(userService.get(USER_ID).isEnabled());
    }
}