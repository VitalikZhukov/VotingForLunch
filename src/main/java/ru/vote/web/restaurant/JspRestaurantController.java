package ru.vote.web.restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vote.model.Restaurant;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@RequestMapping("restaurants")
public class JspRestaurantController extends AbstractRestaurantController{

    public int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("restaurant", new Restaurant("", 0));
        return "restaurantForm";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("restaurant", super.get(getId(request)));
        return "restaurantForm";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        return "redirect:/restaurants";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        Restaurant restaurant = new Restaurant(request.getParameter("name"), Integer.parseInt(request.getParameter("voteCounter")));
        if (request.getParameter("id").isEmpty()) {
            super.create(restaurant);
        } else {
            super.update(restaurant, getId(request));
        }
        return "redirect:/restaurants";
    }
}
