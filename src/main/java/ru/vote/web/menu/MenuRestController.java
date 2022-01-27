package ru.vote.web.menu;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.vote.model.Menu;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = MenuRestController.MENU_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController extends AbstractMenuController{
    static final String MENU_URL = "/rest/profile/menus";

    public ResponseEntity<Menu> createWithLocation(@RequestBody Menu menu) {
        Menu created = super.create(menu);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(MENU_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @GetMapping("/{restaurantId}")
    @ResponseBody
    public List<Menu> getListByRestaurantId(@PathVariable int restaurantId) {
        return super.getListByRestaurantId(restaurantId);
    }

    @Override
    @GetMapping
    @ResponseBody
    public List<Menu> getAll() {
        return super.getAll();
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Menu menu, @PathVariable int id) {
        super.update(menu, id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @DeleteMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllByRestaurantId(@PathVariable int restaurantId) {
        super.deleteAllByRestaurantId(restaurantId);
    }
}
