package ru.vote.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);
    private int restaurantId;

    @GetMapping("/")
    public String root() {
        log.info("root");
        return "redirect:restaurants";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public String getUsers() {
        log.info("root getUsers");
        return "users";
    }

    @GetMapping("/restaurants")
    public String getRestaurants() {
        log.info("root getRestaurants");
        return "restaurants";
    }

    @GetMapping("/menus")
    public String getMenus(@RequestParam Integer restaurantId) {
        if (restaurantId == null) {
            throw new NullPointerException("The restaurant's ID must be not null");
        }
        setRestaurantId(restaurantId);
        log.info("root menus");
        return "menus";
    }

    @GetMapping("/login")
    public String login() {
        log.info("login");
        return "login";
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
