package ru.vote.web.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.vote.model.Menu;
import ru.vote.service.MenuService;

import java.util.List;

@Controller
public class MenuRestController {
    private final Logger log = LoggerFactory.getLogger(MenuRestController.class);

    private final MenuService menuService;

    public MenuRestController(MenuService menuService) {
        this.menuService = menuService;
    }

    public Menu create(Menu menu) {
        log.info("create{}", menu);
        return menuService.create(menu);
    }

    public Menu get(int id) {
        log.info("get {}", id);
        return menuService.get(id);
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

    public void deleteAllByRestaurantId (int restaurant_id) {
        log.info("deleteAllByRestaurantId {}", restaurant_id);
        menuService.deleteAllByRestaurantId(restaurant_id);
    }

}
