package ru.vote.web.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vote.model.Menu;
import ru.vote.service.MenuService;

import java.util.List;

public class AbstractMenuController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService menuService;

    public Menu create(Menu menu) {
        log.info("create{}", menu);
        return menuService.create(menu);
    }

    public List<Menu> getListByRestaurantId(int restaurantId) {
        log.info("getListByRestaurantId {}", restaurantId);
        return menuService.getListByRestaurantId(restaurantId);
    }

    public List<Menu> getAll() {
        log.info("getAll");
        return menuService.getAll();
    }

    public void update(Menu menu, int id) {
        log.info("update{} id = {}", menu, id);
        menuService.update(menu, id);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        menuService.delete(id);
    }

    public void deleteAllByRestaurantId (int restaurantId) {
        log.info("deleteAllByRestaurantId {}", restaurantId);
        menuService.deleteAllByRestaurantId(restaurantId);
    }
}
