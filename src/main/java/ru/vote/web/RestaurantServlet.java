package ru.vote.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.vote.model.Menu;
import ru.vote.model.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        Restaurant restaurant = new Restaurant(
                id.isEmpty() ? null : Integer.valueOf(id),
                req.getParameter("name"));

        if (!id.isEmpty()) {
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
