package ru.vote.web.menu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vote.model.Menu;
import ru.vote.util.exeption.NotFoundException;

import java.util.List;

import static org.junit.Assert.*;
import static ru.vote.MenuTestData.*;
import static ru.vote.UserTestData.NOT_FOUND;


@ContextConfiguration({
        "classpath:spring/spring.xml",
        "classpath:spring/springDB.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/initDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MenuRestControllerTest {

    @Autowired
    private MenuRestController controller;

    @Test
    public void create() {
        Menu created = controller.create(getNew());
        Integer newId = created.getId();
        Menu newMenu = getNew();
        newMenu.setId(newId);
        assertMatch(created, newMenu);
        assertMatch(controller.get(newId), newMenu);
    }

    @Test
    public void update() {
        Menu updated = getUpdated();
        controller.update(updated, updated.getId());
        assertMatch(controller.get(MENU_ID), getUpdated());
    }

    @Test
    public void getListByRestaurantId() {
        List<Menu> menuList = controller.getListByRestaurantId(1);
        assertMatch(menuList, menu1, menu2, menu3);
    }

    @Test
    public void getAll() {
        List<Menu> menuList = controller.getAll();
        assertMatch(menuList, menu1, menu2, menu3, menu4, menu5, menu6);
    }

    @Test
    public void delete() {
        controller.delete(MENU_ID);
        assertThrows(NotFoundException.class, () -> controller.get(MENU_ID));
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> controller.get(NOT_FOUND));
    }
}