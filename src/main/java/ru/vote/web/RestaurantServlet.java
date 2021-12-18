package ru.vote.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;
import ru.vote.model.Menu;
import ru.vote.model.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import ru.vote.web.menu.MenuRestController;
import ru.vote.web.restaurant.RestaurantRestController;

public class RestaurantServlet extends HttpServlet {
    //logging in the controller

    private ConfigurableApplicationContext springContext;
    private RestaurantRestController restaurantController;
    private MenuRestController menuController;

    @Override
    public void init() {
        springContext = new ClassPathXmlApplicationContext("spring/spring.xml", "spring/springDB.xml");
        restaurantController = springContext.getBean(RestaurantRestController.class);
        menuController = springContext.getBean(MenuRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        //user's choose vote
        Integer chooseID = null;
        if (req.getParameter("vote") != null) {
            chooseID = Integer.valueOf(req.getParameter("vote"));
        }


        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(req);
                restaurantController.delete(id);
                resp.sendRedirect("restaurants");
                break;

            case "create":
            case "update":
                final Restaurant restaurant = "create".equals(action) ?
                        new Restaurant("") :
                        restaurantController.get(getId(req));


                req.setAttribute("restaurant", restaurant);
                req.getRequestDispatcher("/restaurantForm.jsp").forward(req, resp);
                break;

            case "all":
            default:
                if(chooseID != null) {
                    req.setAttribute("choose", restaurantController.get(chooseID).getName());
                    restaurantController.incrementVoteCounter(chooseID);
                }
                req.setAttribute("restaurants", restaurantController.getAll());
                req.setAttribute("menu", menuController.getAll());
                req.getRequestDispatcher("/restaurants.jsp").forward(req, resp);
                break;
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");

        Restaurant restaurant = new Restaurant(
                id.isEmpty() ? null : Integer.valueOf(id),
                req.getParameter("name"));

        if (StringUtils.hasLength(id)) {
            restaurantController.update(restaurant, getId(req));
            for (Menu menu : getMenuList(req, restaurant.getId())) {
                menuController.update(menu, menu.getId());
            }
        } else {
            restaurantController.create(restaurant);
            for (Menu menu : getMenuList(req, restaurant.getId())) {
                menuController.create(menu);
            }
        }
        resp.sendRedirect("restaurants");
    }

    private int getId(HttpServletRequest req) {
        String id = Objects.requireNonNull(req.getParameter("id"));
        return Integer.parseInt(id);
    }

    private List<Menu> getMenuList(HttpServletRequest req, int id) {
        List<Menu> menuList = new LinkedList<>();
        int i = 1;
        while (StringUtils.hasLength(req.getParameter("dish" + i))) {
            String dish = req.getParameter("dish" + i);
            double price = Double.parseDouble(req.getParameter("price" + i));
            menuList.add(new Menu(id, dish, price));
            i++;
        }
        return menuList;
    }
}
