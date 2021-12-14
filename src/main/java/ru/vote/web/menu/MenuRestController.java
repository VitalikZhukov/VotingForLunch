package ru.vote.web.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.vote.model.Menu;
import ru.vote.repository.MenuRepository;

import java.util.List;

import static ru.vote.util.ValidationUtil.*;

@Controller
public class MenuRestController {
    private final Logger log = LoggerFactory.getLogger(MenuRestController.class);

    private final MenuRepository menuRepository;

    public MenuRestController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu create(Menu menu) {
        log.info("create{}", menu);
        checkNew(menu);
        return menuRepository.save(menu);
    }

    public Menu get(int id) {
        log.info("get {}", id);
        return checkNotFoundWithId(menuRepository.get(id), id);
    }

    public List<Menu> getAll() {
        log.info("getAll");
        return checkNotFound(menuRepository.getAll());
    }

    public void update(Menu menu, int id) {
        log.info("update{} id = {}", menu, id);
        assureIdConsistent(menu, id);
        menuRepository.save(menu);
    }

    public void delete(int restaurantId) {
        log.info("delete {}", restaurantId);
        menuRepository.delete(restaurantId);
    }

    public List<Menu> getListByRestaurantId(int restaurantId) {
        log.info("getListByRestaurantId {}", restaurantId);
        return checkNotFound(menuRepository.getListByRestaurantId(restaurantId));
    }

}
