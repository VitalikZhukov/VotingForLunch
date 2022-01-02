package ru.vote.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vote.model.Menu;
import ru.vote.model.Restaurant;
import ru.vote.util.ValidationUtil;
import ru.vote.web.menu.MenuRestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("restaurants")
public class JspRestaurantController extends AbstractRestaurantController{

    @Autowired
    private MenuRestController menuRestController;

    public int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("restaurant", new Restaurant("Enter restaurant name", 0));
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
        Restaurant restaurant = new Restaurant(request.getParameter("name"));
        Integer id = request.getParameter("id").isEmpty() ? null : Integer.valueOf(request.getParameter("id"));
        Menu menu1 = new Menu(id, request.getParameter("dish1"), Double.parseDouble(request.getParameter("price1")));
        Menu menu2 = new Menu(id, request.getParameter("dish2"), Double.parseDouble(request.getParameter("price2")));
        Menu menu3 = new Menu(id, request.getParameter("dish3"), Double.parseDouble(request.getParameter("price3")));

        Map<String, String[]> map = request.getParameterMap();

        for (Map.Entry<String, String[]> parse : map.entrySet()) {
            System.out.print(parse.getKey() + " : ");
            for (String str : parse.getValue()) {
                System.out.print(str + ", ");
            }
            System.out.println();
        }


        if (id == null) {
            Restaurant newRestaurant = super.create(restaurant);
            Integer restaurantId = newRestaurant.getId();
            ValidationUtil.checkNotFound(restaurantId);
            menu1.setRestaurantId(restaurantId);
            menu2.setRestaurantId(restaurantId);
            menu3.setRestaurantId(restaurantId);
            menuRestController.create(menu1);
            menuRestController.create(menu2);
            menuRestController.create(menu3);
        } else {
            super.update(restaurant, getId(request));
            //take the menu list by restaurantId, go through it and update
            //checking for new / not new menu
        }
        return "redirect:/restaurants";
    }
}
