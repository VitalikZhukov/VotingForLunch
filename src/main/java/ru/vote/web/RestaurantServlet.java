package ru.vote.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.vote.model.Restaurant;
import ru.vote.repository.RestaurantRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import ru.vote.web.restaurant.RestaurantRestController;

public class RestaurantServlet extends HttpServlet {
    //logging in the controller

    private ConfigurableApplicationContext springContext;
    private RestaurantRestController restaurantController;

    @Override
    public void init() {
        springContext = new ClassPathXmlApplicationContext("spring/spring.xml", "spring/springDB.xml");
        restaurantController = springContext.getBean(RestaurantRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(req);
                restaurantController.delete(id);
                resp.sendRedirect("restaurants");
                break;

            case "create":
            case "update":
                final Restaurant rest = "create".equals(action) ?
                        new Restaurant("", Map.of("", 0.00), 0) :
                        restaurantController.get(getId(req));
                req.setAttribute("restaurant", rest);
                req.getRequestDispatcher("/restaurantForm.jsp").forward(req, resp);
                break;

            case "all":
            default:
                req.setAttribute("restaurants", restaurantController.getAll());
                req.getRequestDispatcher("/restaurants.jsp").forward(req, resp);
                break;
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //user's choose vote
        String chooseRest = req.getParameter("vote");
        if (chooseRest != null) {
            resp.sendRedirect("restaurants");
            return;
        }

        String id = req.getParameter("id");
        String[] menu = req.getParameter("menu").split(", ");
        String[] price = req.getParameter("price").split(", ");
        Map<String, Double> mapMenu = new LinkedHashMap<>();
        for (int i = 0; i < menu.length; i++) {
            mapMenu.put(menu[i], Double.valueOf(price[i]));
        }

        Restaurant restaurant = new Restaurant(id.isEmpty() ? null : Integer.valueOf(id),
                req.getParameter("name"),
                mapMenu,
                Integer.parseInt(req.getParameter("voteCount")));

        if (id != null && !id.isEmpty()) {
            restaurantController.update(restaurant, getId(req));
        } else {
            restaurantController.create(restaurant);
        }
        resp.sendRedirect("restaurants");
    }

    private int getId(HttpServletRequest req) {
        String id = Objects.requireNonNull(req.getParameter("id"));
        return Integer.parseInt(id);
    }
}
