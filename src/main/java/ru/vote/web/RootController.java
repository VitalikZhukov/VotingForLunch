package ru.vote.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

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
    public String getRestaurants() {
        log.info("root getRestaurants");
        return "restaurants";
    }

    @GetMapping("/menus")
    public String getMenus() {
        log.info("root menus");
        return "menus";
    }

    @GetMapping("/login")
    public String login() {
        log.info("login");
        return "login";
    }
}
