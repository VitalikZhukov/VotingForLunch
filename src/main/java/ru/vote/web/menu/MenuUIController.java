package ru.vote.web.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vote.model.Menu;
import ru.vote.web.RootController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@ApiIgnore
@RestController
@RequestMapping(value = "/profile/menus", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuUIController extends AbstractMenuController{

    @Autowired
    private RootController rootController;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(@Valid Menu menu) {
        menu.setRestaurantId(rootController.getRestaurantId());
        if (menu.isNew()) {
            super.create(menu);
        } else {
            super.update(menu, menu.getId());
        }
    }

    @Override
    @GetMapping("/{id}")
    public Menu get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping()
    public List<Menu> getListByRestaurantId() {
        return super.getListByRestaurantId(rootController.getRestaurantId());
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

/*    @Override
    @DeleteMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllByRestaurantId(@PathVariable int restaurantId) {
        super.deleteAllByRestaurantId(restaurantId);
    }*/
}
