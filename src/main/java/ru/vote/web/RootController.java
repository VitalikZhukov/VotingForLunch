package ru.vote.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vote.service.MenuService;
import ru.vote.service.RestaurantService;
import ru.vote.util.SecurityUtil;

import javax.servlet.http.HttpServletRequest;

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
        return "index";
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

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        log.info("root setUser {}", userId);
        SecurityUtil.setAuthUserId(userId);
        return "redirect:restaurants";
    }
}
