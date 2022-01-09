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
@RequestMapping("/restaurants")
public class JspRestaurantController extends AbstractRestaurantController{

    @Autowired
    private MenuRestController menuRestController;

    public int getId(HttpServletRequest request, String paramName) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("restaurant", new Restaurant("Enter restaurant name", 0));
        model.addAttribute("menu1", new Menu("Dish #1", 0.0));
        model.addAttribute("menu2", new Menu("Dish #2", 0.0));
        model.addAttribute("menu3", new Menu("Dish #3", 0.0));
        return "restaurantForm";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("restaurant", super.get(getId(request, "id")));
        List<Menu> menuList = menuRestController.getListByRestaurantId(getId(request, "id"));
        for (int i = 0; i < menuList.size(); i++) {
            model.addAttribute("menu" + (i + 1), menuList.get(i));
        }
        return "restaurantForm";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request, "id"));
        menuRestController.deleteAllByRestaurantId(getId(request, "id"));
        return "redirect:/restaurants";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {

        String voteId = request.getParameter("vote");
        if(voteId != null) {
            super.incrementVoteCounter(Integer.parseInt(voteId));
            return "redirect:/restaurants";
        }

        Restaurant restaurant = new Restaurant(request.getParameter("name"));
        Menu menu1 = new Menu(request.getParameter("dish1"), Double.parseDouble(request.getParameter("price1")));
        Menu menu2 = new Menu(request.getParameter("dish2"), Double.parseDouble(request.getParameter("price2")));
        Menu menu3 = new Menu(request.getParameter("dish3"), Double.parseDouble(request.getParameter("price3")));

        if (request.getParameter("id").isEmpty()) {
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
            int restaurantId = getId(request, "id");
            super.update(restaurant, restaurantId);

            menu1.setRestaurantId(restaurantId);
            menu2.setRestaurantId(restaurantId);
            menu3.setRestaurantId(restaurantId);
            menuRestController.update(menu1, getId(request, "menuId1"));
            menuRestController.update(menu2, getId(request, "menuId2"));
            menuRestController.update(menu3, getId(request, "menuId3"));
        }
        return "redirect:/restaurants";
    }
}
