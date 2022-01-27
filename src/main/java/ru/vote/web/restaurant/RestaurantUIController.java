package ru.vote.web.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vote.model.Restaurant;

import java.util.List;

@RestController
@RequestMapping(value = "/profile/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantUIController extends AbstractRestaurantController{

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestParam String name) {
        super.create(new Restaurant(null, name, 0));
    }

    @Override
    @GetMapping
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

}
