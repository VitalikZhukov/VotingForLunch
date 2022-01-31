package ru.vote.web.menu;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vote.model.Menu;
import ru.vote.util.ValidationUtil;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/profile/menus", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuUIController extends AbstractMenuController{

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> createOrUpdate(@Valid Menu menu, BindingResult result) {
        if (result.hasErrors()) {
            return ValidationUtil.getErrorResponse(result);
        }
        if (menu.isNew()) {
            super.create(menu);
        } else {
            super.update(menu, menu.getId());
        }
        return ResponseEntity.ok().build();
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
