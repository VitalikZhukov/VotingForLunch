package ru.vote.web.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.vote.model.Menu;
import ru.vote.repository.MenuRepository;

import java.util.List;

import static ru.vote.util.ValidationUtil.checkNotFound;

@Controller
public class MenuRestController {
    private final Logger log = LoggerFactory.getLogger(MenuRestController.class);

    private final MenuRepository menuRepository;

    public MenuRestController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public boolean create(Menu menu) {
        log.info("create{}", menu);
        checkNotFound(menu);
        return menuRepository.create(menu);
    }

    public boolean update(Menu menu) {
        log.info("update{}", menu);
        checkNotFound(menu);
        return menuRepository.update(menu);
    }

    public List<Menu> getListByRestaurantId(int restaurantId) {
        log.info("getListByRestaurantId {}", restaurantId);
        return checkNotFound(menuRepository.getListByRestaurantId(restaurantId));
    }

    public List<Menu> getAll() {
        log.info("getAll");
        return checkNotFound(menuRepository.getAll());
    }

    public boolean delete(int restaurantId) {
        log.info("delete {}", restaurantId);
        return menuRepository.delete(restaurantId);
    }

}
