package ru.vote.web.abstractTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vote.ActiveDbProfileResolver;
import ru.vote.model.Menu;
import ru.vote.util.exeption.NotFoundException;
import ru.vote.web.menu.MenuRestController;

import java.util.List;

import static org.junit.Assert.*;
import static ru.vote.MenuTestData.*;


@ContextConfiguration({
        "classpath:spring/spring.xml",
        "classpath:spring/springDB.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/initDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
public abstract class AbstractMenuRestControllerTest {

    @Autowired
    private MenuRestController controller;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setup() {
        cacheManager.getCache("menuCache").clear();
    }

    @Test
    public void create() {
        Menu created = controller.create(getNew());
        Integer newId = created.getId();
        Menu newMenu = getNew();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(controller.get(newId), newMenu);
    }

    @Test
    public void update() {
        Menu updated = getUpdated();
        controller.update(updated, updated.getId());
        MENU_MATCHER.assertMatch(controller.get(MENU_ID), getUpdated());
    }

    @Test
    public void get() {
        Menu menu = controller.get(MENU_ID);
        MENU_MATCHER.assertMatch(menu, menu1);
    }

    @Test
    public void getListByRestaurantId() {
        List<Menu> menuList = controller.getListByRestaurantId(RESTAURANT_ID);
        MENU_MATCHER.assertMatch(menuList, menu1, menu2, menu3);
    }

    @Test
    public void getAll() {
        List<Menu> menuList = controller.getAll();
        MENU_MATCHER.assertMatch(menuList, menu1, menu2, menu3, menu4, menu5, menu6);
    }

    @Test
    public void delete() {
        controller.delete(MENU_ID);
        assertThrows(NotFoundException.class, () -> controller.get(MENU_ID));
    }

    @Test
    public void deleteAllByRestaurantId () {
        controller.deleteAllByRestaurantId(RESTAURANT_ID);
        assertThrows(NotFoundException.class, () -> controller.get(MENU_ID));
        assertThrows(NotFoundException.class, () -> controller.get(MENU_ID + 1));
        assertThrows(NotFoundException.class, () -> controller.get(MENU_ID + 2));
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> controller.get(NOT_FOUND));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> controller.delete(NOT_FOUND));

    }
}