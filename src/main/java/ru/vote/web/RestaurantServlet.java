package ru.vote.web;

import ru.vote.model.Restaurant;
import ru.vote.repository.RestaurantRepository;
import ru.vote.repository.inmemory.InMemoryRestaurantRepository;
import ru.vote.util.RestaurantUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class RestaurantServlet extends HttpServlet {
    private static final Logger log = getLogger(RestaurantServlet.class);

    private RestaurantRepository repository;

    @Override
    public void init() {
        repository = new InMemoryRestaurantRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(req);
                log.info("delete {}", id);
                repository.delete(id);
                resp.sendRedirect("restaurants");
                break;

            case "create":
            case "update":
                log.info("update");
                final Restaurant rest = "create".equals(action) ?
                        new Restaurant("", Map.of("", 0.00), 0) :
                        repository.get(getId(req));
                req.setAttribute("restaurant", rest);
                req.getRequestDispatcher("/restaurantForm.jsp").forward(req, resp);
                break;

            case "all":
            default:
                log.info("getAll");
                req.setAttribute("restaurants", repository.getAll());
                req.getRequestDispatcher("/restaurants.jsp").forward(req, resp);
                break;
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");

        String[] menu = req.getParameter("menu").split(", ");
        String[] price = req.getParameter("price").split(", ");
        Map<String, Double> mapMenu = new LinkedHashMap<>();
        for (int i = 0; i < menu.length; i++) {
            mapMenu.put(menu[i], Double.valueOf(price[i]));
        }

        Restaurant rest = new Restaurant(id.isEmpty() ? null : Integer.valueOf(id),
                req.getParameter("name"),
                mapMenu,
                Integer.parseInt(req.getParameter("voteCount")));
        log.info(rest.isNew() ? "create {}" : "update {}" , rest);
        repository.save(rest);
        resp.sendRedirect("restaurants");
    }

    private int getId(HttpServletRequest req) {
        String id = Objects.requireNonNull(req.getParameter("id"));
        return Integer.parseInt(id);
    }
}
