package ru.vote.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vote.service.MenuService;
import ru.vote.service.RestaurantService;

@Controller
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/")
    public String root() {
        log.info("root");
        return "redirect:restaurants";
    }

    @GetMapping("/users")
    public String getUsers() {
        log.info("root getUsers");
        return "users";
    }

    @GetMapping("/restaurants")
    public String getRestaurants(Model model) {
        log.info("root getRestaurants");
        model.addAttribute("restaurants", restaurantService.getAll());
        return "restaurants";
    }

    @GetMapping("/menus")
    public String getMenus(Model model) {
        log.info("root menus");
        model.addAttribute("menus", menuService.getAll());
        return "menus";
    }

    @GetMapping("/login")
    public String login() {
        log.info("login");
        return "login";
    }
}
