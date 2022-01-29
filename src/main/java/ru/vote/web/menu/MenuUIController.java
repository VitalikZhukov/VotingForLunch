package ru.vote.web.menu;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vote.model.Menu;

import java.util.List;

@RestController
@RequestMapping(value = "/profile/menus", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuUIController extends AbstractMenuController{

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestParam String dish, @RequestParam double price) {
        super.create(new Menu(null, dish, price));
    }

    @Override
    @GetMapping("/{id}")
    public Menu get(@PathVariable int id) {
        return super.get(id);
    }

/*    @Override
    @GetMapping("/{restaurantId}")
    public List<Menu> getListByRestaurantId(@PathVariable int restaurantId) {
        return super.getListByRestaurantId(restaurantId);
    }*/

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
