package ru.vote.service.abstractTest;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.vote.model.Menu;
import ru.vote.service.MenuService;
import ru.vote.util.exeption.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.Assert.*;
import static ru.vote.MenuTestData.*;


public abstract class AbstractMenuServiceTest extends AbstractServiceTest {

    @Autowired
    private MenuService menuService;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setup() {
        cacheManager.getCache("menuCache").clear();
    }

    @Test
    public void create() {
        Menu created = menuService.create(getNew());
        Integer newId = created.getId();
        Menu newMenu = getNew();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(menuService.get(newId), newMenu);
    }

    @Test
    public void update() {
        Menu updated = getUpdated();
        menuService.update(updated, updated.getId());
        MENU_MATCHER.assertMatch(menuService.get(MENU_ID), getUpdated());
    }

    @Test
    public void get() {
        Menu menu = menuService.get(MENU_ID);
        MENU_MATCHER.assertMatch(menu, menu1);
    }

    @Test
    public void getListByRestaurantId() {
        List<Menu> menuList = menuService.getListByRestaurantId(RESTAURANT_ID);
        MENU_MATCHER.assertMatch(menuList, menu1, menu2, menu3);
    }

    @Test
    public void getAll() {
        List<Menu> menuList = menuService.getAll();
        MENU_MATCHER.assertMatch(menuList, menu1, menu2, menu3, menu4, menu5, menu6);
    }

    @Test
    public void delete() {
        menuService.delete(MENU_ID);
        assertThrows(NotFoundException.class, () -> menuService.get(MENU_ID));
    }

    @Test
    public void deleteAllByRestaurantId () {
        menuService.deleteAllByRestaurantId(RESTAURANT_ID);
        assertThrows(NotFoundException.class, () -> menuService.get(MENU_ID));
        assertThrows(NotFoundException.class, () -> menuService.get(MENU_ID + 1));
        assertThrows(NotFoundException.class, () -> menuService.get(MENU_ID + 2));
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> menuService.get(NOT_FOUND));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> menuService.delete(NOT_FOUND));
    }

    @Test
    public void createWithException() throws Exception {
        Assume.assumeTrue("Validation not supported (JPA only)", isJpaBased());
        validateRootCause(ConstraintViolationException.class, () -> menuService.create(new Menu(null, null, "Menu", 50.5)));
        validateRootCause(ConstraintViolationException.class, () -> menuService.create(new Menu(null, 10000, " ", 50.5)));
        validateRootCause(ConstraintViolationException.class, () -> menuService.create(new Menu(null, 10000, "Menu", null)));

    }
}